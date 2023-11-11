package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.converter.DecisionEntityConverter;
import com.kakao.golajuma.vote.domain.exception.decision.CompletionVoteException;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateDecisionService {
	private final DecisionEntityConverter entityConverter;
	private final DecisionRepository decisionRepository;
	private final OptionRepository optionRepository;
	private final VoteRepository voteRepository;
	private final DecisionResponseConverter responseConverter;

	public DecisionResponse execute(final Long userId, final Long optionId) {
		VoteEntity requestVote = findVote(optionId);
		OptionEntity requestOption = findOption(optionId);
		validateVoteStatus(requestVote);

		deleteExistDecisionByVote(userId, requestVote);

		saveDecision(userId, requestOption);

		return generateResponse(requestVote, optionId);
	}

	private void saveDecision(final Long userId, final OptionEntity optionEntity) {
		DecisionEntity entity = entityConverter.from(userId, optionEntity.getId());
		decisionRepository.save(entity);
		optionEntity.updateCount();
	}

	private void deleteExistDecisionByVote(final Long userId, final VoteEntity voteEntity) {
		List<OptionEntity> options = optionRepository.findAllByVoteId(voteEntity.getId());

		options.forEach(
				option -> {
					decisionRepository
							.findByUserIdAndOptionId(userId, option.getId())
							.ifPresent(
									existDecision -> {
										decisionRepository.delete(existDecision);
										option.decreaseCount();
									});
				});
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

	private DecisionResponse generateResponse(final VoteEntity voteEntity, final Long selectId) {
		List<OptionEntity> optionsByVote = optionRepository.findAllByVoteId(voteEntity.getId());
		return responseConverter.from(selectId, optionsByVote, voteEntity.getVoteTotalCount());
	}
}
