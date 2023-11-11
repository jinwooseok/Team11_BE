package com.kakao.golajuma.vote.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateDecisionServiceTest {
	@Mock private DecisionEntityConverter entityConverter;
	@Mock private DecisionRepository decisionRepository;
	@Mock private OptionRepository optionRepository;
	@Mock private VoteRepository voteRepository;
	@Mock private DecisionResponseConverter responseConverter;
	@InjectMocks private CreateDecisionService createDecisionService;

	@Test
	@DisplayName("존재하지 않은 vote에 대한 투표 요청 시 예외가 발생한다.")
	void exception_when_not_found_vote() {
		// given
		when(voteRepository.findVoteByOption(1L)).thenThrow(NotFoundVoteException.class);

		// when & then
		assertThrows(NotFoundVoteException.class, () -> createDecisionService.execute(1L, 1L));
	}

	@Test
	@DisplayName("존재하지 않은 option에 대한 투표 요청 시 예외가 발생한다.")
	void exception_when_not_found_option() {
		// given
		VoteEntity voteEntity = VoteEntity.builder().id(1L).build();

		when(voteRepository.findVoteByOption(1L)).thenReturn(Optional.of(voteEntity));
		when(optionRepository.findById(1L)).thenThrow(NotFoundOptionException.class);

		// when & then
		assertThrows(NotFoundOptionException.class, () -> createDecisionService.execute(1L, 1L));
	}

	@Test
	@DisplayName("만료된 vote에 대해 투표 요청 시 예외가 발생한다.")
	void exception_when_closed_vote() {
		// given
		VoteEntity voteEntity = Mockito.mock(VoteEntity.class);

		OptionEntity optionEntity = OptionEntity.builder().id(1L).build();

		when(voteRepository.findVoteByOption(1L)).thenReturn(Optional.of(voteEntity));
		when(optionRepository.findById(1L)).thenReturn(Optional.of(optionEntity));

		when(voteEntity.isComplete()).thenReturn(true);

		// when & then
		assertThrows(CompletionVoteException.class, () -> createDecisionService.execute(1L, 1L));
	}

	@Test
	@DisplayName("이미 해당 vote에 대해 투표를 했을 시 예외가 발생한다.")
	void exception_when_already_decision() {
		// give
		VoteEntity voteEntity = Mockito.mock(VoteEntity.class);

		OptionEntity optionEntity = OptionEntity.builder().id(1L).build();

		when(voteRepository.findVoteByOption(any())).thenReturn(Optional.of(voteEntity));
		when(optionRepository.findById(any())).thenReturn(Optional.of(optionEntity));
		when(optionRepository.findAllByVoteId(any())).thenReturn(List.of(optionEntity));
		when(decisionRepository.findByUserIdAndOptionId(any(), any()))
				.thenReturn(Optional.of(mock(DecisionEntity.class)));

		// when & then
		assertThrows(ExistsDecisionException.class, () -> createDecisionService.execute(1L, 1L));
	}
}
