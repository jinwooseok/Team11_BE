package com.kakao.golajuma.vote.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.vote.domain.exception.vote.NotFoundVoteException;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.GetVoteDetailResponse;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetVoteDetailServiceTest {
	@InjectMocks GetVoteDetailService getVoteDetailService;
	@Mock VoteRepository voteRepository;
	@Mock GetVoteService getVoteService;
	@Mock VoteEntity vote;
	@Mock VoteDto voteDto;

	@DisplayName("투표 상세 조회 정상 응답")
	@Test
	public void getVoteDetailTest() {
		// when
		when(voteRepository.findById((Long) any())).thenReturn(Optional.of(vote));
		when(getVoteService.execute(any(), any())).thenReturn(voteDto);

		GetVoteDetailResponse result = getVoteDetailService.execute(1L, 1L);

		// then
		assertEquals(voteDto, result.getVote());
	}

	@DisplayName("투표 상세 조회 예외 - 존재하지 않는 투표")
	@Test
	public void getVoteDetailTest_NotFoundVote() {
		// when
		when(voteRepository.findById((Long) any())).thenThrow(new NotFoundVoteException());

		// then
		assertThrows(NotFoundVoteException.class, () -> getVoteDetailService.execute(0L, 1L));
	}
}
