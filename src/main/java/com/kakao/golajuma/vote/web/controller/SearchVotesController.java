package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.auth.web.support.AnonymousAvailable;
import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.SearchVotesService;
import com.kakao.golajuma.vote.web.controller.converter.GetVotesRequestConverter;
import com.kakao.golajuma.vote.web.dto.response.SearchVotesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SearchVotesController {

	private final SearchVotesService searchVotesService;
	private final GetVotesRequestConverter getVotesRequestConverter;

	@AnonymousAvailable
	@GetMapping("/votes/search")
	public ApiResponse<ApiResponseBody.SuccessBody<SearchVotesResponse>> searchVoteList(
			@Login Long userId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "current") String sort,
			@RequestParam(defaultValue = "total") String category) {
		SearchVotesResponse responseDto =
				searchVotesService.execute(
						userId,
						page,
						keyword,
						getVotesRequestConverter.toSort(sort),
						getVotesRequestConverter.toCategory(category));

		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.GET);
	}
}
