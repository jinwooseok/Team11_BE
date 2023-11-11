package com.kakao.golajuma.auth.web.controller;

import com.kakao.golajuma.auth.domain.service.LoginUserService;
import com.kakao.golajuma.auth.domain.service.ReissueService;
import com.kakao.golajuma.auth.web.dto.request.LoginUserRequest;
import com.kakao.golajuma.auth.web.dto.response.TokenResponse;
import com.kakao.golajuma.auth.web.support.CookieExtractor;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody.SuccessBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	private final LoginUserService loginUserUseCase;
	private final ReissueService reissueService;
	private final CookieExtractor cookieExtractor;

	@PostMapping("/login")
	public ApiResponse<SuccessBody<TokenResponse>> signIn(
			@RequestBody @Valid LoginUserRequest request) {
		final TokenResponse tokenResponse = loginUserUseCase.execute(request);
		return ApiResponseGenerator.success(tokenResponse, HttpStatus.OK, MessageCode.CREATE);
	}

	@PostMapping("/reissue")
	public ApiResponse<SuccessBody<TokenResponse>> reissue(HttpServletRequest request) {
		final String token = cookieExtractor.extract(request);
		final TokenResponse tokenResponse = reissueService.execute(token);

		return ApiResponseGenerator.success(tokenResponse, HttpStatus.OK, MessageCode.CREATE);
	}
}
