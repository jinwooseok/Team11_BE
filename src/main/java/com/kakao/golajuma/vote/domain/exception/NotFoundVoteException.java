package com.kakao.golajuma.vote.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundVoteException extends BusinessException {

	public NotFoundVoteException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}
