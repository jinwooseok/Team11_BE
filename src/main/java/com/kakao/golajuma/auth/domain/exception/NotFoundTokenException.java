package com.kakao.golajuma.auth.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundTokenException extends BusinessException {
	private static final String MESSAGE = "존재하지 않은 토큰입니다.";

	public NotFoundTokenException() {
		super(MESSAGE, HttpStatus.NOT_FOUND);
	}
}
