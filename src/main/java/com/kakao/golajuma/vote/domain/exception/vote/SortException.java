package com.kakao.golajuma.vote.domain.exception.vote;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class SortException extends BusinessException {
	private static final String MESSAGE = "잘못된 요청입니다. sort";

	public SortException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
