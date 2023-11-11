package com.kakao.golajuma.auth.domain.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenFormatException extends AuthorizationException {
	private static final String MESSAGE = "token 형식이 맞지 않습니다.";

	public InvalidTokenFormatException() {
		super(MESSAGE, HttpStatus.FORBIDDEN);
	}
}
