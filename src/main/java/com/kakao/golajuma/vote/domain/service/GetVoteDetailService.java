package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.vote.domain.exception.RequestParamException;
import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.OptionJPARepository;
import com.kakao.golajuma.vote.infra.repository.VoteJPARepository;
import com.kakao.golajuma.vote.web.dto.response.GetVoteDetailResponse;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetVoteDetailService {

	private final VoteJPARepository voteJPARepository;
	private final OptionJPARepository optionJPARepository;
	private final UserRepository userRepository;

	public GetVoteDetailResponse getVoteDetail(long voteId, long userId) {
		// 투표와 옵션리스트 가져오기
		VoteEntity vote = voteJPARepository.findById(voteId);
		List<OptionEntity> options = optionJPARepository.findAllByVoteId(vote.getId());

		boolean on = checkActive(vote.getVoteActive());

		boolean isOwner = vote.isOwner(userId);

		//			boolean participate = desicionJPARepository.findByUserIdAndVoteId(userId, vote.getId());
		boolean participate = true;

		// 각 옵션에 투표했는지 여부 판단
		// 근데 참여 안했으면 확인할 필요가 없음 -> 무조건 다 false 임
		List<Boolean> choiceList = checkChoiceOption(options, userId);

		// 유저 닉네임 가져오기
		UserEntity user = userRepository.findById(userId).get();
		String username = user.getNickname();

		VoteDto voteDto =
				VoteDto.makeDto(vote, on, username, isOwner, participate, options, choiceList);

		return new GetVoteDetailResponse(voteDto);
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
}
