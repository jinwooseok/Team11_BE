package com.kakao.golajuma.auth.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundEmailException extends BusinessException {
	private static final String MESSAGE = "존재하지 않은 이메일입니다.";

	public NotFoundEmailException() {
		super(MESSAGE, HttpStatus.NOT_FOUND);
	}
}
