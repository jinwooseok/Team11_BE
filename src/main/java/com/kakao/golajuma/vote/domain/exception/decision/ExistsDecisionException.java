package com.kakao.golajuma.vote.domain.exception.decision;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ExistsDecisionException extends BusinessException {
	private static final String MESSAGE = "해당 vote에 이미 투표했습니다.";

	public ExistsDecisionException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
