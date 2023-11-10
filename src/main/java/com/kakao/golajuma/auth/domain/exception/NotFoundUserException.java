package com.kakao.golajuma.auth.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundUserException extends BusinessException {
	private static final String MESSAGE = "존재하지 않은 유저입니다.";

	public NotFoundUserException() {
		super(MESSAGE, HttpStatus.NOT_FOUND);
	}
}
