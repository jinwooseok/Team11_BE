package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.RequestParamException;
import com.kakao.golajuma.vote.infra.entity.Category;
import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.OptionJPARepository;
import com.kakao.golajuma.vote.infra.repository.VoteJPARepository;
import com.kakao.golajuma.vote.web.dto.response.GetVoteListResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetVoteListService {

	private final VoteJPARepository voteJPARepository;
	private final OptionJPARepository optionJPARepository;
	//    private final DesicionJPARepository desicionJPARepository;

	static int page = 0;
	static int size = 10;

	public GetVoteListResponse.MainAndFinishPage getVoteList(
			String sort, String active, String category) {
		/*
		투표 중 active = continue 이고, createdDate가 최신순으로 정렬하여 가져와서 보여준다
		사용자의 id를 가져와서 참여한 투표와 참여하지 않은 투표를 다른 데이터 형식으로 반환한다.
		*/
		long userId = 1;
		GetVoteListResponse.MainAndFinishPage responseBody =
				new GetVoteListResponse.MainAndFinishPage();

		// 진행중인 투표(on) or 완료된 투표 요청 판단
		boolean on = checkActive(active);

		// 1. vote list 를 가져온다
		List<VoteEntity> voteList = findByRepository(sort, active, checkCategory(category));

		// 마지막 페이지인지 검사
		boolean isLast = false;
		if (voteList.size() <= size) {
			isLast = true;
		}

		// 2. 각 vote 별로 vote option 을 찾는다
		for (VoteEntity vote : voteList) {
			List<OptionEntity> options = optionJPARepository.findAllByVoteId(vote.getId());
			boolean isOwner = (userId == vote.getUserId());
			//			boolean participate = desicionJPARepository.findByUserIdAndVoteId(userId, vote.getId());
			boolean participate = true;
			long totalCount = vote.getVoteTotalCount();

			// 여기서 문제 완료된 페이지 요청인 경우 투표 옵션 count를 무조건 보여줘야함
			responseBody.toDto(vote, on, isOwner, participate, totalCount, options, isLast);
		}
		return responseBody;
	}

	public boolean checkActive(String active) {
		if (active.equals("continue")) {
			return true;
		} else if (active.equals("finish")) {
			return false;
		} else {
			throw new RequestParamException("잘못된 요청입니다.(active)");
		}
	}

	public Category checkCategory(String category) {
		return Category.findCategory(category);
	}

	public List<VoteEntity> findByRepository(String sort, String active, Category category) {
		// 어디서부터 몇개씩 가져올건지
		Pageable pageable = PageRequest.of(page, size);

		List<VoteEntity> voteList;
		if (sort.equals("current")) {
			voteList =
					voteJPARepository.findAllByActiveAndCategoryOrderByCreatedDate(
							active, category, pageable);
		} else if (sort.equals("popular")) {
			voteList =
					voteJPARepository.findAllByActiveAndCategoryOrderByVoteTotalCount(
							active, category, pageable);
		} else {
			throw new RequestParamException("잘못된 요청입니다.(sort)");
		}
		return voteList;
	}

	public GetVoteListResponse.MyPage getVoteListInMyPageByParticipate() {
		// 임의 유저값 가져옴 나중에 유효성 처리 해야함
		long userId = 1;
		GetVoteListResponse.MyPage responseBody = new GetVoteListResponse.MyPage();

		// userId가 투표한 투표 리스트를 decision 레포에서 찾음
		//        List<VoteEntity> voteList = decisionJPARepository.findAllUserId(userId);
		List<VoteEntity> voteList = new ArrayList<>();
		responseBody.toDto(voteList);

		return responseBody;
	}

	public GetVoteListResponse.MyPage getVoteListInMyPageByAsk() {
		// 임의 유저값 가져옴 나중에 유효성 처리 해야함
		long userId = 1;
		GetVoteListResponse.MyPage responseBody = new GetVoteListResponse.MyPage();

		// userId가 올린 투표를 가져옴
		List<VoteEntity> voteList = voteJPARepository.findAllByUserId(userId);
		responseBody.toDto(voteList);

		return responseBody;
	}
}
