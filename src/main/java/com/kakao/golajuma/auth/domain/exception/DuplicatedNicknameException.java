package com.kakao.golajuma.auth.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class DuplicatedNicknameException extends BusinessException {
	private static final String MESSAGE = "중복되는 이메일입니다.";

	public DuplicatedNicknameException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
