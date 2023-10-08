package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.vote.domain.exception.RequestParamException;
import com.kakao.golajuma.vote.infra.entity.Category;
import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.OptionJPARepository;
import com.kakao.golajuma.vote.infra.repository.VoteJPARepository;
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

	private final VoteJPARepository voteJPARepository;
	private final OptionJPARepository optionJPARepository;
	private final UserRepository userRepository;
	//    private final DesicionJPARepository desicionJPARepository;

	static int page = 0;
	static int size = 5;

	public GetVoteListResponse.MainAndFinishPage getVoteList(
			long userId, long idx, long totalCount, String sort, String active, String category) {
		/*
		투표 중 active = continue 이고, createdDate가 최신순으로 정렬하여 가져와서 보여준다
		사용자의 id를 가져와서 참여한 투표와 참여하지 않은 투표를 다른 데이터 형식으로 반환한다.
		*/
		// 응답 dto
		GetVoteListResponse.MainAndFinishPage responseBody =
				new GetVoteListResponse.MainAndFinishPage();

		// 진행중인 투표(on) or 완료된 투표 요청 판단
		boolean on = checkActive(active);

		// 1. vote list 를 가져온다
		Slice<VoteEntity> voteList =
				findByRepository(idx, totalCount, sort, active, checkCategory(category));

		// 마지막 페이지인지 검사
		responseBody.isLast(voteList.isLast());

		// 2. 각 vote 별로 vote option 을 찾는다 - slice 방식
		for (VoteEntity vote : voteList) {
			List<OptionEntity> options = optionJPARepository.findAllByVoteId(vote.getId());
			boolean isOwner = vote.isOwner(userId);
			//			boolean participate = desicionJPARepository.findByUserIdAndVoteId(userId, vote.getId());
			boolean participate = true;

			// 참여했다면 어느 옵션 id에 투표했는지 알아야함
			List<Boolean> choiceList = checkChoiceOption(options, userId);

			// 작성자 명을 가져온다
			UserEntity user = userRepository.findById(userId).get();
			String username = user.getNickname();
			// 여기서 문제 완료된 페이지 요청인 경우 투표 옵션 count를 무조건 보여줘야함
			VoteDto voteDto =
					VoteDto.makeDto(vote, on, username, isOwner, participate, options, choiceList);
			responseBody.addVote(voteDto);
		}

		return responseBody;
	}

	public List<Boolean> checkChoiceOption(List<OptionEntity> options, long userId) {
		List<Boolean> choiceList = new ArrayList<>();
		//
		for (OptionEntity option : options) {
			long optionId = option.getId();
			// decision repo 에서 확인해야함
			//			if(decisionJPARepository.checkByUserIdAndOptionId(userId, optionId))
			//				choiceList.add(true);
			//			else choiceList.add(false);
			choiceList.add(true); // dummy data
		}
		return choiceList;
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
