package com.kakao.golajuma.vote.domain.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.GetVotesResponse;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@ExtendWith(MockitoExtension.class)
class GetHotVotesServiceTest {
	@InjectMocks private GetHotVotesService getHotVotesService;

	@Mock private GetVoteService getVoteService;

	@Mock private VoteRepository voteRepository;

	@Nested
	@DisplayName("핫게시판 요청 성공 시 이전 1시간 동안 투표 수가 가장 많은 순서로 리스트를 반환한다.")
	class success_get_hot_vote_service {
		@Test
		@DisplayName("현재 시각으로부터 1시간 전 사이에 해당하는 투표 리스트를 반환한다.")
		void get_hot_vote_list_success_test() {
			// given
			VoteEntity voteEntity =
					VoteEntity.builder().id(1L).voteTitle("title").voteContent("content").build();

			List<VoteEntity> voteEntityList = new ArrayList<>();
			voteEntityList.add(voteEntity);

			Slice<VoteEntity> voteEntitySlice =
					new SliceImpl<>(voteEntityList, Pageable.unpaged(), false);

			when(voteRepository.findByTimeLimitAndDecisionCount(any(), any(), any()))
					.thenReturn(voteEntitySlice);

			when(getVoteService.execute(voteEntity, 1L))
					.thenReturn(new VoteDto(voteEntity, "username", "continue", false, false, "total"));

			// when
			GetVotesResponse.MainAndFinishPage result = getHotVotesService.execute(1L, 0);
			// then
			assertThat(result.getVotes().get(0).getClass()).isEqualTo(VoteDto.class);
			assertThat(result.getVotes().get(0).getId()).isEqualTo(1L);
			assertThat(result.getVotes().get(0).getTitle()).isEqualTo("title");
			assertThat(result.getVotes().get(0).getContent()).isEqualTo("content");
			assertThat(result.getVotes().get(0).getActive()).isEqualTo("continue");
			assertThat(result.getIsLast()).isTrue();
		}
	}
}
