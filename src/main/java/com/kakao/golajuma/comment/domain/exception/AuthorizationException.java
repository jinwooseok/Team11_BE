package com.kakao.golajuma.comment.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends BusinessException {
	public AuthorizationException(String message) {
		super(message, HttpStatus.UNAUTHORIZED);
	}
}
