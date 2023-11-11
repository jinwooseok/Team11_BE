package com.kakao.golajuma.vote.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.vote.persistence.entity.Category;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.SearchVotesResponse;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@ExtendWith(MockitoExtension.class)
class SearchVotesServiceTest {

	@InjectMocks SearchVotesService searchVotesService;
	@Mock VoteRepository voteRepository;
	@Mock GetVoteService getVoteService;

	String keyword;
	Sort sort;
	Category category;
	VoteEntity voteEntity;
	List<VoteEntity> votes;
	Slice<VoteEntity> voteList;

	private void setup() {
		// VoteEntity 생성
		voteEntity =
				VoteEntity.builder()
						.id(1L)
						.userId(1L)
						.voteTitle("title")
						.voteEndDate(LocalDateTime.now())
						.category(Category.TOTAL)
						.build();
		// vote list -> vote slice 생성
		votes = new ArrayList<>();
		votes.add(voteEntity);
		voteList = new SliceImpl<>(votes);

		VoteDto voteDto = new VoteDto(voteEntity, "username", "continue", true, true, "total");

		// when
		when(getVoteService.execute(any(), any())).thenReturn(voteDto);
	}

	private void normalThen(SearchVotesResponse result) {
		assertEquals(1, result.getVotes().size());
		assertEquals(true, result.getIsLast());
	}

	@DisplayName("투표 검색 정상 요청 - 최신순, 전체 카테고리")
	@Test
	void searchVoteTest_currentAndTotalCategory() {
		setup();
		// given
		keyword = "keyword";
		sort = Sort.CURRENT;
		category = Category.TOTAL;

		// when
		when(voteRepository.searchVotesOrderByCreatedDate(any(), any())).thenReturn(voteList);

		SearchVotesResponse result = searchVotesService.execute(1L, 0, keyword, sort, category);
		// then
		normalThen(result);
	}

	@DisplayName("투표 검색 정상 요청 - 최신순, 특정 카테고리")
	@Test
	void searchVoteTest_currentAndSpecificCategory() {
		setup();
		// given
		keyword = "keyword";
		sort = Sort.CURRENT;
		category = Category.BUY;

		// when
		when(voteRepository.searchVotesByCategoryOrderByCreatedDate(any(), any(), any()))
				.thenReturn(voteList);

		SearchVotesResponse result = searchVotesService.execute(1L, 0, keyword, sort, category);
		// then
		normalThen(result);
	}

	@DisplayName("투표 검색 정상 요청 - 인기순, 전체 카테고리")
	@Test
	void searchVoteTest_popularAndTotalCategory() {
		setup();
		// given
		keyword = "keyword";
		sort = Sort.POPULAR;
		category = Category.TOTAL;

		// when
		when(voteRepository.searchVotesOrderByVoteTotalCount(any(), any())).thenReturn(voteList);

		SearchVotesResponse result = searchVotesService.execute(1L, 0, keyword, sort, category);
		// then
		normalThen(result);
	}

	@DisplayName("투표 검색 정상 요청 - 인기순, 특정 카테고리")
	@Test
	void searchVoteTest_popularAndSpecificCategory() {
		setup();
		// given
		keyword = "keyword";
		sort = Sort.POPULAR;
		category = Category.BUY;

		// when
		when(voteRepository.searchVotesByCategoryOrderByVoteTotalCount(any(), any(), any()))
				.thenReturn(voteList);

		SearchVotesResponse result = searchVotesService.execute(1L, 0, keyword, sort, category);
		// then
		normalThen(result);
	}
}
