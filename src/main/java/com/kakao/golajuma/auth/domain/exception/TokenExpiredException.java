package com.kakao.golajuma.auth.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class TokenExpiredException extends BusinessException {
	private static final String MESSAGE = "토큰이 만료되었습니다.";

	public TokenExpiredException() {
		super(MESSAGE, HttpStatus.FORBIDDEN);
	}
}
