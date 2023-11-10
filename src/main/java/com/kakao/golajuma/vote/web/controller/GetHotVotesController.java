package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.auth.web.support.AnonymousAvailable;
import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.GetHotVotesService;
import com.kakao.golajuma.vote.web.dto.response.GetVotesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetHotVotesController {
	private final GetHotVotesService getHotVotesService;

	@AnonymousAvailable
	@GetMapping("/votes/hot")
	public ApiResponse<ApiResponseBody.SuccessBody<GetVotesResponse.MainAndFinishPage>> readHotVotes(
			@Login Long userId, @RequestParam(defaultValue = "0") int page) {
		GetVotesResponse.MainAndFinishPage responseDto = getHotVotesService.execute(userId, page);
		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.GET);
	}
}
