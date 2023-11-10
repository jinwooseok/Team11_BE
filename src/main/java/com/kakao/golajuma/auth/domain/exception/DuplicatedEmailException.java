package com.kakao.golajuma.auth.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DuplicatedEmailException extends BusinessException {
	private static final String MESSAGE = "중복되는 닉네임입니다";

	public DuplicatedEmailException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
