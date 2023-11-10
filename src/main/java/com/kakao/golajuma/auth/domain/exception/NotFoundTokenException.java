package com.kakao.golajuma.auth.domain.exception;

import org.springframework.http.HttpStatus;

public class NotFoundTokenException extends AuthorizationException {
	private static final String MESSAGE = "존재하지 않은 토큰입니다.";

	public NotFoundTokenException() {
		super(MESSAGE, HttpStatus.NOT_FOUND);
	}
}
