package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.converter.DecisionEntityConverter;
import com.kakao.golajuma.vote.domain.exception.decision.CompletionVoteException;
import com.kakao.golajuma.vote.domain.exception.decision.ExistsDecisionException;
import com.kakao.golajuma.vote.domain.exception.vote.NotFoundOptionException;
import com.kakao.golajuma.vote.domain.exception.vote.NotFoundVoteException;
import com.kakao.golajuma.vote.persistence.entity.DecisionEntity;
import com.kakao.golajuma.vote.persistence.entity.OptionEntity;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.DecisionRepository;
import com.kakao.golajuma.vote.persistence.repository.OptionRepository;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.converter.DecisionResponseConverter;
import com.kakao.golajuma.vote.web.dto.response.DecisionResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateDecisionService {
	private final DecisionEntityConverter entityConverter;
	private final DecisionRepository decisionRepository;
	private final OptionRepository optionRepository;
	private final VoteRepository voteRepository;
	private final DecisionResponseConverter responseConverter;

	public DecisionResponse execute(final Long userId, final Long optionId) {
		VoteEntity voteEntity = findVote(optionId);
		OptionEntity optionEntity = findOption(optionId);

		boolean isExist = existDecisionByVote(userId, voteEntity.getId());
		if (isExist) {
			throw new ExistsDecisionException();
		}

		saveDecision(userId, voteEntity, optionEntity);

		return generateResponse(voteEntity, optionId);
	}

	private boolean existDecisionByVote(Long userId, Long voteId) {
		List<OptionEntity> options = optionRepository.findAllByVoteId(voteId);

		return options.stream()
				.map(option -> decisionRepository.findByUserIdAndOptionId(userId, option.getId()))
				.anyMatch(Optional::isPresent);
	}

	private void saveDecision(
			final Long userId, final VoteEntity voteEntity, final OptionEntity optionEntity) {
		validateVoteStatus(voteEntity);

		updateCounts(voteEntity, optionEntity);

		DecisionEntity entity = entityConverter.from(userId, optionEntity.getId());
		decisionRepository.save(entity);
	}

	private OptionEntity findOption(final Long optionId) {
		return optionRepository.findById(optionId).orElseThrow(NotFoundOptionException::new);
	}

	private VoteEntity findVote(final Long optionId) {
		return voteRepository.findVoteByOption(optionId).orElseThrow(NotFoundVoteException::new);
	}

	private void validateVoteStatus(final VoteEntity voteEntity) {
		if (voteEntity.isComplete()) {
			throw new CompletionVoteException();
		}
	}

	private void updateCounts(final VoteEntity voteEntity, final OptionEntity optionEntity) {
		voteEntity.updateCount();
		optionEntity.updateCount();
	}

	private DecisionResponse generateResponse(final VoteEntity voteEntity, final Long selectId) {
		List<OptionEntity> optionsByVote = optionRepository.findAllByVoteId(voteEntity.getId());
		return responseConverter.from(selectId, optionsByVote, voteEntity.getVoteTotalCount());
	}
}
