package com.kakao.golajuma.auth.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class InvalidTokenFormatException extends BusinessException {
	private static final String MESSAGE = "token 형식이 맞지 않습니다.";

	public InvalidTokenFormatException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
