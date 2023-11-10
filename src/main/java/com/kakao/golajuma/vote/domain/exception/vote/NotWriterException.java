package com.kakao.golajuma.vote.domain.exception.vote;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotWriterException extends BusinessException {
	private static final String MESSAGE = "투표 작성자가 아닙니다.";

	public NotWriterException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
