package com.kakao.golajuma.vote.domain.exception.vote;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundOptionException extends BusinessException {
	private static final String MESSAGE = "존재하지 않는 옵션 입니다.";

	public NotFoundOptionException() {
		super(MESSAGE, HttpStatus.NOT_FOUND);
	}
}
