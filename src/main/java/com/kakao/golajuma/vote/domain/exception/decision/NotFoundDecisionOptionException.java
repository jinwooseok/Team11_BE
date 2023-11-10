package com.kakao.golajuma.vote.domain.exception.decision;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundDecisionOptionException extends BusinessException {
	private static final String MESSAGE = "해당 옵션에 투표한 적이 없습니다.";

	public NotFoundDecisionOptionException() {
		super(MESSAGE, HttpStatus.NOT_FOUND);
	}
}
