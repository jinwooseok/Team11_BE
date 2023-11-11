package com.kakao.golajuma.vote.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.vote.persistence.entity.Category;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.GetVotesResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetAskedVotesServiceTest {
	@InjectMocks GetAskedVotesService getAskedVotesService;
	@Mock VoteRepository voteRepository;

	VoteEntity voteEntity;
	List<VoteEntity> votes;

	private void myPageNormalThen(GetVotesResponse.MyPage result) {
		assertEquals("title", result.getVotes().get(0).getTitle());
		assertEquals("complete", result.getVotes().get(0).getActive());
		assertEquals(1, result.getVotes().size());
	}

	@DisplayName("마이페이지 게시한 투표 조회 정상 요청")
	@Test
	public void myPageAskTest() {
		// given
		Long userId = 1L;

		// VoteEntity 생성
		voteEntity =
				VoteEntity.builder()
						.id(1L)
						.userId(1L)
						.voteTitle("title")
						.voteEndDate(LocalDateTime.now().minusMinutes(3))
						.category(Category.TOTAL)
						.build();
		votes = new ArrayList<>();
		votes.add(voteEntity);

		// when
		when(voteRepository.findAllByUserId(any())).thenReturn(votes);

		GetVotesResponse.MyPage result = getAskedVotesService.execute(userId);
		// then
		myPageNormalThen(result);
	}
}
