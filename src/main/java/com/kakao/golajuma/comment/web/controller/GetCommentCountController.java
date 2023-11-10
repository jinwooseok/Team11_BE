package com.kakao.golajuma.comment.web.controller;

import com.kakao.golajuma.auth.web.support.AnonymousAvailable;
import com.kakao.golajuma.comment.domain.service.GetCommentCountService;
import com.kakao.golajuma.comment.web.dto.response.GetCommentCountResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/votes/{voteId}/comments/count")
public class GetCommentCountController {
	private final GetCommentCountService getCommentCountService;

	@AnonymousAvailable
	@GetMapping
	public ApiResponse<ApiResponseBody.SuccessBody<GetCommentCountResponse>> getCommentCount(
			@PathVariable Long voteId) {
		GetCommentCountResponse response = getCommentCountService.execute(voteId);
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.GET);
	}
}
