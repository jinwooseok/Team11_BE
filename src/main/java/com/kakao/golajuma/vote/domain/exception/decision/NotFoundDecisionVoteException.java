package com.kakao.golajuma.vote.domain.exception.decision;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundDecisionVoteException extends BusinessException {
	private static final String MESSAGE = "해당 vote에 대한 투표가 없습니다.";

	public NotFoundDecisionVoteException() {
		super(MESSAGE, HttpStatus.NOT_FOUND);
	}
}
