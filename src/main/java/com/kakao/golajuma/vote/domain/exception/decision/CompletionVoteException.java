package com.kakao.golajuma.vote.domain.exception.decision;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CompletionVoteException extends BusinessException {
	private static final String MESSAGE = "종료된 투표에 대해선 참여할 수 없습니다.";

	public CompletionVoteException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
