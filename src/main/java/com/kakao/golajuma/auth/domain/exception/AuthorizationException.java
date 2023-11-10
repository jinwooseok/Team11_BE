package com.kakao.golajuma.auth.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends BusinessException {
	public AuthorizationException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}
}
