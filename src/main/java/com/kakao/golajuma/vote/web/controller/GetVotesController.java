package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.auth.web.support.AnonymousAvailable;
import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.GetAskedVotesService;
import com.kakao.golajuma.vote.domain.service.GetParticipatedVotesService;
import com.kakao.golajuma.vote.domain.service.GetVotesService;
import com.kakao.golajuma.vote.web.controller.converter.GetVotesRequestConverter;
import com.kakao.golajuma.vote.web.dto.response.GetVotesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetVotesController {

	private final GetVotesService getVoteListService;
	private final GetAskedVotesService getAskedVotesService;
	private final GetParticipatedVotesService getParticipatedVotesService;
	private final GetVotesRequestConverter getVotesRequestConverter;

	@AnonymousAvailable
	@GetMapping("/votes")
	public ApiResponse<ApiResponseBody.SuccessBody<GetVotesResponse.MainAndFinishPage>> getVoteList(
			@Login Long userId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "current") String sort,
			@RequestParam(defaultValue = "continue") String active,
			@RequestParam(defaultValue = "total") String category) {

		GetVotesResponse.MainAndFinishPage responseDto =
				getVoteListService.execute(
						userId,
						page,
						getVotesRequestConverter.toSort(sort),
						getVotesRequestConverter.toActive(active),
						getVotesRequestConverter.toCategory(category));

		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.GET);
	}

	@GetMapping("/users/votes/participate")
	public ApiResponse<ApiResponseBody.SuccessBody<GetVotesResponse.MyPage>>
			getVoteListInMyPageByParticipate(@Login Long userId) {
		GetVotesResponse.MyPage responseDto = getParticipatedVotesService.execute(userId);

		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.GET);
	}

	@GetMapping("/users/votes/ask")
	public ApiResponse<ApiResponseBody.SuccessBody<GetVotesResponse.MyPage>> getVoteListInMyPageByAsk(
			@Login Long userId) {
		GetVotesResponse.MyPage responseDto = getAskedVotesService.execute(userId);

		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.GET);
	}
}
