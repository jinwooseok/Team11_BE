package com.kakao.golajuma.auth.web.controller;

import com.kakao.golajuma.auth.domain.service.ReadUserProfileService;
import com.kakao.golajuma.auth.web.dto.response.UserProfileResponse;
import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class UserProfileController {

	private final ReadUserProfileService readUserProfileService;

	@GetMapping
	public ApiResponse<ApiResponseBody.SuccessBody<UserProfileResponse>> readUserProfile(
			@Login Long userId) {

		UserProfileResponse responseData = readUserProfileService.execute(userId);

		return ApiResponseGenerator.success(responseData, HttpStatus.OK, MessageCode.GET);
	}
}
