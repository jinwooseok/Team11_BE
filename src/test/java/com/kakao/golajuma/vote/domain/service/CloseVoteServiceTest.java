package com.kakao.golajuma.vote.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.vote.domain.exception.vote.AlreadyCloseException;
import com.kakao.golajuma.vote.domain.exception.vote.NotFoundVoteException;
import com.kakao.golajuma.vote.domain.exception.vote.NotWriterException;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CloseVoteServiceTest {
	@InjectMocks private CloseVoteService closeVoteService;
	@Mock VoteRepository voteRepository;

	@DisplayName("투표 마감 정상 응답")
	@Test
	public void closeVoteTest() {
		// given
		Long voteId = 1L;
		Long writerId = 1L;
		Long requestUserId = 1L;
		LocalDateTime date = LocalDateTime.now().plusMinutes(240);
		VoteEntity vote = VoteEntity.builder().id(voteId).userId(writerId).voteEndDate(date).build();

		// when
		when(voteRepository.findById((Long) any())).thenReturn(Optional.of(vote));

		closeVoteService.execute(voteId, requestUserId);
		// 분 단위까지만 비교
		LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
		LocalDateTime closeDate = vote.getVoteEndDate().truncatedTo(ChronoUnit.MINUTES);

		// then
		// 현재 시각과 종료된 시각이 일치하는지 검사
		assertEquals(now, closeDate);
	}

	@DisplayName("존재 하지 않는 투표에 대한 마감 실패")
	@Test
	public void closeVoteTest_notFoundVote() {
		// given
		Long voteId = -1L;
		Long requestUserId = 1L;

		// when & then
		assertThrows(
				NotFoundVoteException.class, () -> closeVoteService.execute(voteId, requestUserId));
	}

	@DisplayName("작성자가 아닌 경우 투표에 대한 마감 실패")
	@Test
	public void closeVoteTest_notWriter() {
		// given
		Long voteId = 1L;
		Long writerId = 1L;
		Long requestUserId = 2L;
		VoteEntity vote =
				VoteEntity.builder()
						.id(voteId)
						.userId(writerId)
						.voteEndDate(LocalDateTime.now().plusMinutes(240))
						.build();

		// when
		when(voteRepository.findById((Long) any())).thenReturn(Optional.of(vote));

		// then
		assertThrows(NotWriterException.class, () -> closeVoteService.execute(voteId, requestUserId));
	}

	@DisplayName("이미 완료된 투표인 경우 마감 실패")
	@Test
	public void closeVoteTest_alreadyComplete() {
		// given

		Long voteId = 1L;
		Long writerId = 1L;
		Long requestUserId = 1L;
		VoteEntity vote =
				VoteEntity.builder()
						.id(voteId)
						.userId(writerId)
						.voteEndDate(LocalDateTime.now().minusMinutes(240))
						.build();

		// when
		when(voteRepository.findById((Long) any())).thenReturn(Optional.of(vote));

		// then
		assertThrows(
				AlreadyCloseException.class, () -> closeVoteService.execute(voteId, requestUserId));
	}
}
