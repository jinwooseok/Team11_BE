package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import com.kakao.golajuma.vote.persistence.entity.Active;
import com.kakao.golajuma.vote.persistence.entity.OptionEntity;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.DecisionRepository;
import com.kakao.golajuma.vote.persistence.repository.OptionRepository;
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
		List<OptionEntity> optionEntities = optionRepository.findAllByVoteId(voteEntity.getId());
		UserEntity userEntity = findWriter(voteEntity);

		boolean isOwner = voteEntity.isOwner(userId);

		Active active = voteEntity.checkActive();

		List<? super OptionDto> options = new ArrayList<>();
		List<Boolean> choices = checkChoiceOptions(optionEntities, userId);
		boolean participate = checkParticipate(choices);

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
		return decisionRepository.existsByUserIdAndOptionId(userId, optionEntity.getId());
	}

	private boolean checkParticipate(List<Boolean> choices) {
		return choices.contains(Boolean.TRUE);
	}
}
