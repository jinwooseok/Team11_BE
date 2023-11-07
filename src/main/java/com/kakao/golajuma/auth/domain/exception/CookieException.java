package com.kakao.golajuma.auth.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CookieException extends BusinessException {
	public CookieException() {
		super("쿠키가 존재하지 않습니다.", HttpStatus.UNAUTHORIZED);
	}
}
