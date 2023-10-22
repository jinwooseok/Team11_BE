package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.RequestParamException;
import com.kakao.golajuma.vote.infra.entity.Active;
import com.kakao.golajuma.vote.infra.entity.Category;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.DecisionRepository;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.GetVoteListResponse;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetVoteListService {

	private final VoteRepository voteRepository;
	private final GetVoteService getVoteService;
	private final DecisionRepository decisionRepository;

	static int page = 0;
	static int size = 5;

	public GetVoteListResponse.MainAndFinishPage getVoteList(
			long userId, int page, String sort, String active, String category) {
		/*
		투표 중 active = continue 이고, createdDate가 최신순으로 정렬하여 가져와서 보여준다
		사용자의 id를 가져와서 참여한 투표와 참여하지 않은 투표를 다른 데이터 형식으로 반환한다.
		*/
		this.page = page;

		// 1. vote list 를 가져온다
		// 투표 리스트 가져 오는 방법
		// 정렬 기준
		// 1. 메인페이지 or 완료된 페이지 active
		// 2. 전체 카테고리 or 세부 카테고리
		// 3. 최신순 or 인기순
		Slice<VoteEntity> voteList = getVoteListByRequest(active, category, sort);

		List<VoteDto> votes = new ArrayList<>();
		for (VoteEntity vote : voteList) {
			VoteDto voteDto = getVoteService.getVote(vote, userId);
			votes.add(voteDto);
		}
		// 마지막 페이지인지 검사
		boolean isLast = voteList.isLast();

		return new GetVoteListResponse.MainAndFinishPage(votes, isLast);
	}

	private Slice<VoteEntity> getVoteListByRequest(String active, String category, String sort) {
		if (isContinueRequest(active)) {
			return findContinueVotes(category, sort);
		}
		return findCompleteVotes(category, sort);
	}

	private Slice<VoteEntity> findCompleteVotes(String category, String sort) {
		if (getCategory(category) == Category.TOTAL) {
			return completeOrderBySort(sort);
		}
		return completeByCategoryOrderBySort(getCategory(category), sort);
	}

	private Slice<VoteEntity> completeOrderBySort(String sort) {
		// 어디서부터 몇개씩 가져올건지
		Pageable pageable = PageRequest.of(page, size);
		LocalDateTime now = LocalDateTime.now();

		if (isCurrent(sort)) {
			return voteRepository.findAllFinishVotesOrderByCreatedDate(now, pageable);
		}
		return voteRepository.findAllFinishVotesOrderByVoteTotalCount(now, pageable);
	}

	private Slice<VoteEntity> completeByCategoryOrderBySort(Category category, String sort) {
		// 어디서부터 몇개씩 가져올건지
		Pageable pageable = PageRequest.of(page, size);

		LocalDateTime now = LocalDateTime.now();

		if (isCurrent(sort)) {
			return voteRepository.findAllFinishVotesByCategoryOrderByCreatedDate(now, category, pageable);
		}
		return voteRepository.findAllFinishVotesByCategoryOrderByVoteTotalCount(
				now, category, pageable);
	}

	private Slice<VoteEntity> findContinueVotes(String category, String sort) {
		if (getCategory(category) == Category.TOTAL) {
			return continueOrderBySort(sort);
		}
		return continueByCategoryOrderBySort(getCategory(category), sort);
	}

	private Slice<VoteEntity> continueOrderBySort(String sort) {
		// 어디서부터 몇개씩 가져올건지
		Pageable pageable = PageRequest.of(page, size);

		LocalDateTime now = LocalDateTime.now();

		if (isCurrent(sort)) {
			return voteRepository.findAllContinueVotesOrderByCreatedDate(now, pageable);
		}
		return voteRepository.findAllContinueVotesOrderByVoteTotalCount(now, pageable);
	}

	private Slice<VoteEntity> continueByCategoryOrderBySort(Category category, String sort) {
		// 어디서부터 몇개씩 가져올건지
		Pageable pageable = PageRequest.of(page, size);

		LocalDateTime now = LocalDateTime.now();

		if (isCurrent(sort)) {
			return voteRepository.findAllContinueVotesByCategoryOrderByCreatedDate(
					now, category, pageable);
		}
		return voteRepository.findAllContinueVotesByCategoryOrderByVoteTotalCount(
				now, category, pageable);
	}

	private boolean isCurrent(String sort) {
		return Sort.findSort(sort) == Sort.CURRENT;
	}

	private boolean isContinueRequest(String active) {
		if (Active.findActive(active) == Active.CONTINUE) {
			return true;
		}
		if (Active.findActive(active) == Active.COMPLETE) {
			return false;
		}
		throw new RequestParamException("잘못된 요청입니다.(active)");
	}

	private Category getCategory(String category) {
		return Category.findCategory(category);
	}

	public GetVoteListResponse.MyPage getVoteListInMyPageByParticipate(long userId) {
		// 임의 유저값 가져옴 나중에 유효성 처리 해야함
		GetVoteListResponse.MyPage responseBody = new GetVoteListResponse.MyPage();

		// userId가 투표한 투표 리스트를 decision 레포에서 찾아야함
		//		        List<VoteEntity> voteList = decisionRepository.findAllUserId(userId);
		List<VoteEntity> voteList = new ArrayList<>();
		responseBody.toDto(voteList);

		return responseBody;
	}

	public GetVoteListResponse.MyPage getVoteListInMyPageByAsk(long userId) {
		// 임의 유저값 가져옴 나중에 유효성 처리 해야함
		GetVoteListResponse.MyPage responseBody = new GetVoteListResponse.MyPage();

		// userId가 올린 투표를 가져옴
		List<VoteEntity> voteList = voteRepository.findAllByUserId(userId);
		responseBody.toDto(voteList);

		return responseBody;
	}
}
