package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.vote.infra.entity.Active;
import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.DecisionRepository;
import com.kakao.golajuma.vote.infra.repository.OptionRepository;
import com.kakao.golajuma.vote.web.dto.response.CountOptionDto;
import com.kakao.golajuma.vote.web.dto.response.OptionDto;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetVoteService {
	private final OptionRepository optionRepository;
	private final UserRepository userRepository;
	private final DecisionRepository decisionRepository;

	public VoteDto execute(VoteEntity voteEntity, Long userId) {
		// 투표의 옵션을 찾는다
		List<OptionEntity> optionEntities = optionRepository.findAllByVoteId(voteEntity.getId());
		// 작성자 찾기
		UserEntity userEntity = findWriter(voteEntity);

		boolean isOwner = voteEntity.isOwner(userId);

		Active active = voteEntity.checkActive();

		List<? super OptionDto> options = new ArrayList<>();
		// 참여했는지 여부 판단
		List<Boolean> choices = checkChoiceOptions(optionEntities, userId);
		boolean participate = checkParticipate(choices);

		// case 1 : 질문자, isOwner : true, participate : false, 옵션 카운트 표시
		// case 2 : 응답자, 참여 O, isOwner : false, participate : true, 옵션 카운트 표시
		// case 3 : 응답자, 참여 X, isOwner : false, participate : false, 옵션 카운트 미표시
		// 투표가 진행되고 있는 상태에서(on) && 주인이 아니고 && 참여하지 않았을때만 옵션 Count를 보여주지 않음 그냥 OptionDto
		if (voteEntity.isOn() && !isOwner && !participate) {
			for (OptionEntity optionEntity : optionEntities) {
				OptionDto optionDto = OptionDto.convert(optionEntity);
				options.add(optionDto);
			}
		} else {
			for (int i = 0; i < optionEntities.size(); i++) {
				OptionEntity optionEntity = optionEntities.get(i);
				boolean choice = choices.get(i);
				CountOptionDto countOptionDto =
						CountOptionDto.convert(optionEntity, choice, voteEntity.getVoteTotalCount());
				options.add(countOptionDto);
			}
		}
		String category = getCategory(voteEntity);

		return VoteDto.convert(voteEntity, userEntity, active, isOwner, participate, category, options);
	}

	private UserEntity findWriter(VoteEntity voteEntity) {
		Long writerId = voteEntity.getUserId();
		return userRepository.findById(writerId).get();
	}

	private String getCategory(VoteEntity voteEntity) {
		return voteEntity.getCategory().getCategory();
	}

	private List<Boolean> checkChoiceOptions(List<OptionEntity> optionEntities, Long userId) {
		List<Boolean> chocieList = new ArrayList<>();
		for (OptionEntity optionEntity : optionEntities) {
			chocieList.add(checkChoiceOption(userId, optionEntity));
		}
		return chocieList;
	}

	private boolean checkChoiceOption(Long userId, OptionEntity optionEntity) {
		// decision repo 탐색
		return decisionRepository.existsByUserIdAndOptionId(userId, optionEntity.getId());
	}

	private boolean checkParticipate(List<Boolean> choices) {
		return choices.contains(Boolean.TRUE);
	}
}
