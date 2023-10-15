package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.RequestParamException;
import com.kakao.golajuma.vote.infra.entity.Category;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.GetVoteListResponse;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
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

	private final VoteRepository voteJPARepository;
	private final GetVoteService getVoteService;

	static int page = 0;
	static int size = 5;

	public GetVoteListResponse.MainAndFinishPage getVoteList(
			long userId, long idx, long totalCount, String sort, String active, String category) {
		/*
		투표 중 active = continue 이고, createdDate가 최신순으로 정렬하여 가져와서 보여준다
		사용자의 id를 가져와서 참여한 투표와 참여하지 않은 투표를 다른 데이터 형식으로 반환한다.
		*/

		// 진행중인 투표(on) or 완료된 투표 요청 판단
		boolean on = checkActive(active);

		// 1. vote list 를 가져온다
		Slice<VoteEntity> voteList =
				findByRepository(idx, totalCount, sort, active, checkCategory(category));

		List<VoteDto> votes = new ArrayList<>();
		// 2. 각 vote 별로 vote option 을 찾는다 - slice 방식
		for (VoteEntity vote : voteList) {
			VoteDto voteDto = getVoteService.getVote(vote, userId, on);
			votes.add(voteDto);
		}
		// 마지막 페이지인지 검사
		boolean isLast = voteList.isLast();

		return new GetVoteListResponse.MainAndFinishPage(votes, isLast);
	}

	public boolean checkActive(String active) {
		if (active.equals("continue")) {
			return true;
		}
		if (active.equals("finish")) {
			return false;
		}
		throw new RequestParamException("잘못된 요청입니다.(active)");
	}

	public Category checkCategory(String category) {
		return Category.findCategory(category);
	}

	public Slice<VoteEntity> findByRepository(
			long idx, long totalCount, String sort, String active, Category category) {
		// 어디서부터 몇개씩 가져올건지
		Pageable pageable = PageRequest.of(page, size);

		if (sort.equals("current")) {
			return voteJPARepository.findAllByActiveAndCategoryOrderByCreatedDate(
					idx, active, category, pageable);
		}
		if (sort.equals("popular")) {
			return voteJPARepository.findAllByActiveAndCategoryOrderByVoteTotalCount(
					totalCount, active, category, pageable);
		}

		throw new RequestParamException("잘못된 요청입니다.(sort)");
	}

	public GetVoteListResponse.MyPage getVoteListInMyPageByParticipate(long userId) {
		// 임의 유저값 가져옴 나중에 유효성 처리 해야함
		GetVoteListResponse.MyPage responseBody = new GetVoteListResponse.MyPage();

		// userId가 투표한 투표 리스트를 decision 레포에서 찾아야함
		//        List<VoteEntity> voteList = decisionJPARepository.findAllUserId(userId);
		List<VoteEntity> voteList = new ArrayList<>();
		responseBody.toDto(voteList);

		return responseBody;
	}

	public GetVoteListResponse.MyPage getVoteListInMyPageByAsk(long userId) {
		// 임의 유저값 가져옴 나중에 유효성 처리 해야함
		GetVoteListResponse.MyPage responseBody = new GetVoteListResponse.MyPage();

		// userId가 올린 투표를 가져옴
		List<VoteEntity> voteList = voteJPARepository.findAllByUserId(userId);
		responseBody.toDto(voteList);

		return responseBody;


	}
}
