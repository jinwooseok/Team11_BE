package com.kakao.golajuma.vote.domain.exception.vote;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AlreadyCloseException extends BusinessException {
	private static final String MESSAGE = "이미 종료된 투표입니다.";

	public AlreadyCloseException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
