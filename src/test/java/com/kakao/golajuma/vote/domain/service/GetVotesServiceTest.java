package com.kakao.golajuma.vote.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.vote.persistence.entity.Active;
import com.kakao.golajuma.vote.persistence.entity.Category;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.GetVotesResponse;
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
public class GetVotesServiceTest {

	@InjectMocks GetVotesService getVotesService;
	@Mock VoteRepository voteRepository;
	@Mock GetVoteService getVoteService;

	Long userId = 1L;
	int page = 0;
	Sort sort;
	Active active;
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

	private void mainAndCompletePageNormalThen(GetVotesResponse.MainAndFinishPage result) {
		assertEquals(true, result.getIsLast());
		assertEquals(1, result.getVotes().size());
	}

	@DisplayName("메인페이지 조회 정상 응답 - 최신순, 전체 카테고리")
	@Test
	public void mainPageTest_currentAndTotalCategory() {
		setup();
		// given
		sort = Sort.CURRENT;
		active = Active.CONTINUE;
		category = Category.TOTAL;

		// when
		when(voteRepository.findAllContinueVotesOrderByCreatedDate(any(), any())).thenReturn(voteList);

		GetVotesResponse.MainAndFinishPage result =
				getVotesService.execute(userId, page, sort, active, category);
		// then
		mainAndCompletePageNormalThen(result);
	}

	@DisplayName("메인페이지 조회 정상 응답 - 최신순, 특정 카테고리")
	@Test
	public void mainPageTest_currentAndSpecificCategory() {
		setup();
		// given
		sort = Sort.CURRENT;
		active = Active.CONTINUE;
		category = Category.FOOD;

		// when
		when(voteRepository.findAllContinueVotesByCategoryOrderByCreatedDate(any(), any(), any()))
				.thenReturn(voteList);

		GetVotesResponse.MainAndFinishPage result =
				getVotesService.execute(userId, page, sort, active, category);
		// then
		mainAndCompletePageNormalThen(result);
	}

	@DisplayName("메인페이지 조회 정상 응답 - 인기순, 전체 카테고리")
	@Test
	public void mainPageTest_popularAndTotalCategory() {
		setup();
		// given
		sort = Sort.POPULAR;
		active = Active.CONTINUE;
		category = Category.TOTAL;

		// when
		when(voteRepository.findAllContinueVotesOrderByVoteTotalCount(any(), any()))
				.thenReturn(voteList);

		GetVotesResponse.MainAndFinishPage result =
				getVotesService.execute(userId, page, sort, active, category);
		// then
		mainAndCompletePageNormalThen(result);
	}

	@DisplayName("메인페이지 조회 정상 응답 - 인기순, 특정 카테고리")
	@Test
	public void mainPageTest_popularAndSpecificCategory() {
		setup();
		// given
		sort = Sort.POPULAR;
		active = Active.CONTINUE;
		category = Category.WHAT;

		// when
		when(voteRepository.findAllContinueVotesByCategoryOrderByVoteTotalCount(any(), any(), any()))
				.thenReturn(voteList);

		GetVotesResponse.MainAndFinishPage result =
				getVotesService.execute(userId, page, sort, active, category);
		// then
		mainAndCompletePageNormalThen(result);
	}

	@DisplayName("완료된 페이지 조회 정상 응답 - 최신순, 전체 카테고리")
	@Test
	public void completePageTest_currentAndTotalCategory() {
		setup();
		// given
		sort = Sort.CURRENT;
		active = Active.COMPLETE;
		category = Category.TOTAL;

		// when
		when(voteRepository.findAllFinishVotesOrderByCreatedDate(any(), any())).thenReturn(voteList);

		GetVotesResponse.MainAndFinishPage result =
				getVotesService.execute(userId, page, sort, active, category);
		// then
		mainAndCompletePageNormalThen(result);
	}

	@DisplayName("완료된 페이지 조회 정상 응답 - 최신순, 특정 카테고리")
	@Test
	public void completePageTest_currentAndSpecificCategory() {
		setup();
		// given
		sort = Sort.CURRENT;
		active = Active.COMPLETE;
		category = Category.FOOD;

		// when
		when(voteRepository.findAllFinishVotesByCategoryOrderByCreatedDate(any(), any(), any()))
				.thenReturn(voteList);

		GetVotesResponse.MainAndFinishPage result =
				getVotesService.execute(userId, page, sort, active, category);
		// then
		mainAndCompletePageNormalThen(result);
	}

	@DisplayName("완료된 페이지 조회 정상 응답 - 인기순, 전체 카테고리")
	@Test
	public void completePageTest_popularAndTotalCategory() {
		setup();
		// given
		sort = Sort.POPULAR;
		active = Active.COMPLETE;
		category = Category.TOTAL;

		// when
		when(voteRepository.findAllFinishVotesOrderByVoteTotalCount(any(), any())).thenReturn(voteList);

		GetVotesResponse.MainAndFinishPage result =
				getVotesService.execute(userId, page, sort, active, category);
		// then
		mainAndCompletePageNormalThen(result);
	}

	@DisplayName("완료된 페이지 조회 정상 응답 - 인기순, 특정 카테고리")
	@Test
	public void completePageTest_popularAndSpecificCategory() {
		setup();
		// given
		sort = Sort.POPULAR;
		active = Active.COMPLETE;
		category = Category.WHAT;

		// when
		when(voteRepository.findAllFinishVotesByCategoryOrderByVoteTotalCount(any(), any(), any()))
				.thenReturn(voteList);

		GetVotesResponse.MainAndFinishPage result =
				getVotesService.execute(userId, page, sort, active, category);
		// then
		mainAndCompletePageNormalThen(result);
	}
}
