package com.kakao.golajuma.auth.domain.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationTokenNotFoundException extends AuthorizationException {
	private static final String MESSAGE = "인증 토큰이 존재하지 않습니다.";

	public AuthenticationTokenNotFoundException() {
		super(MESSAGE, HttpStatus.FORBIDDEN);
	}
}
