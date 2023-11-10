package com.kakao.golajuma.vote.domain.exception.vote;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ActiveException extends BusinessException {
	private static final String MESSAGE = "잘못된 요청입니다. active";

	public ActiveException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
