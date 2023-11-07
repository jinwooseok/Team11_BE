package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.auth.domain.exception.NotFoundException;
import com.kakao.golajuma.vote.domain.converter.DecisionEntityConverter;
import com.kakao.golajuma.vote.domain.exception.CompletionVoteException;
import com.kakao.golajuma.vote.domain.exception.ExistsDecisionException;
import com.kakao.golajuma.vote.domain.exception.NotFoundDecisionException;
import com.kakao.golajuma.vote.infra.entity.DecisionEntity;
import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.DecisionRepository;
import com.kakao.golajuma.vote.infra.repository.OptionRepository;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
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
public class DecisionService {

	private final DecisionEntityConverter entityConverter;
	private final DecisionRepository decisionRepository;
	private final OptionRepository optionRepository;
	private final VoteRepository voteRepository;
	private final DecisionResponseConverter responseConverter;

	/**
	 * 옵션에 대한 투표를 한다.
	 *
	 * @param userId 유저 식별자
	 * @param optionId 옵션 식별자
	 * @throws ExistsDecisionException 해당 옵션에 대해 이미 투표를 진행했을 시 에외 처리
	 * @throws NotFoundDecisionException 존재하지 않는 vote에 투표 했을 시 에외 처리
	 * @throws CompletionVoteException 종료된 투표에 대해 투표를 변경했을 시 에외 처리
	 * @throws ExistsDecisionException 해당 vote에 이미 투표했을 경우 에외 처리
	 */
	public DecisionResponse createDecision(final Long userId, final Long optionId) {
		boolean isExist = existDecisionByVote(userId, optionId);
		if (isExist) {
			throw new ExistsDecisionException("해당 vote에 이미 투표했습니다.");
		}

		saveDecision(userId, optionId);

		return generateResponse(findVote(optionId), optionId);
	}

	private void saveDecision(final Long userId, final Long optionId) {
		VoteEntity voteEntity = findVote(optionId);
		OptionEntity optionEntity = findOption(optionId);

		validateVoteStatus(voteEntity);

		updateCounts(voteEntity, optionEntity);

		DecisionEntity entity = entityConverter.from(userId, optionId);
		decisionRepository.save(entity);
	}

	/**
	 * 옵션에 대한 투표를 취소한다.
	 *
	 * @param userId 유저 식별자
	 * @param optionId 옵션 식별자
	 * @throws NotFoundDecisionException 해당 옵션에 투표를 한 기록이 없을 시 예외 처리
	 * @throws NotFoundDecisionException 존재하지 않는 vote에 투표 했을 시 예외 처리
	 * @throws CompletionVoteException 종료된 투표에 대해 투표를 변경했을 시 에외 처리
	 */
	public DecisionResponse deleteVote(final Long userId, final Long optionId) {
		DecisionEntity decisionEntity = findDecision(userId, optionId);

		deleteDecision(decisionEntity, optionId);

		return generateResponse(findVote(optionId));
	}

	private void deleteDecision(final DecisionEntity decisionEntity, final Long optionId) {
		OptionEntity optionEntity = findOption(optionId);
		VoteEntity voteEntity = findVote(optionId);

		validateVoteStatus(voteEntity);

		decisionRepository.delete(decisionEntity);
		decreaseCounts(voteEntity, optionEntity);
	}

	/**
	 * 옵션에 대한 투표를 업데이트 한다.
	 *
	 * @param userId 유저 식별자
	 * @param optionId 옵션 식별자
	 * @throws NotFoundDecisionException 해당 옵션에 투표를 한 기록이 없을 시 에러가 발생한다.
	 * @throws NotFoundDecisionException 존재하지 않는 vote에 투표 했을 시 에러가 발생한다.
	 * @throws CompletionVoteException 종료된 투표에 대해 투표를 변경했을 시 에러가 발생한다.
	 */
	public DecisionResponse updateVote(final Long userId, final Long optionId) {
		boolean isExist = existDecisionByVote(userId, optionId);
		if (!isExist) {
			throw new NotFoundDecisionException("해당 vote에 대한 투표가 없습니다.");
		}

		DecisionEntity existDecision = findExistDecisionByVote(userId, optionId);

		deleteDecision(existDecision, optionId);
		saveDecision(userId, optionId);

		return generateResponse(findVote(optionId), optionId);
	}

	private DecisionEntity findDecision(final Long userId, final Long optionId) {
		return decisionRepository
				.findByUserIdAndOptionId(userId, optionId)
				.orElseThrow(() -> new NotFoundDecisionException("해당 옵션에 투표한 적이 없습니다."));
	}

	private OptionEntity findOption(final Long optionId) {
		return optionRepository
				.findById(optionId)
				.orElseThrow(() -> new NotFoundException("존재하지 않는 옵션 입니다."));
	}

	private boolean existDecisionByVote(Long userId, Long optionId) {
		VoteEntity vote = findVote(optionId);

		List<OptionEntity> options = optionRepository.findAllByVoteId(vote.getId());

		return options.stream()
				.map(option -> decisionRepository.findByUserIdAndOptionId(userId, option.getId()))
				.anyMatch(Optional::isPresent);
	}

	private DecisionEntity findExistDecisionByVote(Long userId, Long optionId) {
		VoteEntity vote = findVote(optionId);

		List<OptionEntity> options = optionRepository.findAllByVoteId(vote.getId());

		return options.stream()
				.map(option -> decisionRepository.findByUserIdAndOptionId(userId, option.getId()))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.findAny()
				.orElseThrow(() -> new NotFoundDecisionException("해당 vote에 투표한 적이 없습니다."));
	}

	private VoteEntity findVote(final Long optionId) {
		return voteRepository
				.findVoteByOption(optionId)
				.orElseThrow(() -> new NotFoundException("존재하지 않는 투표 입니다."));
	}

	private void validateVoteStatus(final VoteEntity voteEntity) {
		if (voteEntity.isComplete()) {
			throw new CompletionVoteException("종료된 투표에 대해선 참여할 수 없습니다.");
		}
	}

	private void updateCounts(final VoteEntity voteEntity, final OptionEntity optionEntity) {
		voteEntity.updateCount();
		optionEntity.updateCount();
	}

	private void decreaseCounts(final VoteEntity voteEntity, final OptionEntity optionEntity) {
		voteEntity.decreaseCount();
		optionEntity.decreaseCount();
	}

	private DecisionResponse generateResponse(final VoteEntity voteEntity, final Long selectId) {
		List<OptionEntity> optionsByVote = optionRepository.findAllByVoteId(voteEntity.getId());
		return responseConverter.from(selectId, optionsByVote, voteEntity.getVoteTotalCount());
	}

	private DecisionResponse generateResponse(final VoteEntity voteEntity) {
		List<OptionEntity> optionsByVote = optionRepository.findAllByVoteId(voteEntity.getId());
		return responseConverter.from(optionsByVote, voteEntity.getVoteTotalCount());
	}
}
