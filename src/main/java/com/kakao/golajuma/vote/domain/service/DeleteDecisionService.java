package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.decision.CompletionVoteException;
import com.kakao.golajuma.vote.domain.exception.decision.NotFoundDecisionOptionException;
import com.kakao.golajuma.vote.domain.exception.vote.NotFoundOptionException;
import com.kakao.golajuma.vote.domain.exception.vote.NotFoundVoteException;
import com.kakao.golajuma.vote.infra.entity.DecisionEntity;
import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.DecisionRepository;
import com.kakao.golajuma.vote.infra.repository.OptionRepository;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.converter.DecisionResponseConverter;
import com.kakao.golajuma.vote.web.dto.response.DecisionResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteDecisionService {
	private final DecisionRepository decisionRepository;
	private final OptionRepository optionRepository;
	private final VoteRepository voteRepository;
	private final DecisionResponseConverter responseConverter;

	public DecisionResponse execute(final Long userId, final Long optionId) {
		VoteEntity voteEntity = findVote(optionId);
		OptionEntity optionEntity = findOption(optionId);

		DecisionEntity decisionEntity = findDecision(userId, optionEntity.getId());

		deleteDecision(voteEntity, optionEntity, decisionEntity);

		return generateResponse(findVote(optionId));
	}

	private DecisionEntity findDecision(final Long userId, final Long optionId) {
		return decisionRepository
				.findByUserIdAndOptionId(userId, optionId)
				.orElseThrow(NotFoundDecisionOptionException::new);
	}

	private void deleteDecision(
			final VoteEntity voteEntity,
			final OptionEntity optionEntity,
			final DecisionEntity decisionEntity) {
		validateVoteStatus(voteEntity);

		decisionRepository.delete(decisionEntity);
		decreaseCounts(voteEntity, optionEntity);
	}

	private VoteEntity findVote(final Long optionId) {
		return voteRepository.findVoteByOption(optionId).orElseThrow(NotFoundVoteException::new);
	}

	private OptionEntity findOption(final Long optionId) {
		return optionRepository.findById(optionId).orElseThrow(NotFoundOptionException::new);
	}

	private void validateVoteStatus(final VoteEntity voteEntity) {
		if (voteEntity.isComplete()) {
			throw new CompletionVoteException();
		}
	}

	private void decreaseCounts(final VoteEntity voteEntity, final OptionEntity optionEntity) {
		voteEntity.decreaseCount();
		optionEntity.decreaseCount();
	}

	private DecisionResponse generateResponse(final VoteEntity voteEntity) {
		List<OptionEntity> optionsByVote = optionRepository.findAllByVoteId(voteEntity.getId());
		return responseConverter.from(optionsByVote, voteEntity.getVoteTotalCount());
	}
}
