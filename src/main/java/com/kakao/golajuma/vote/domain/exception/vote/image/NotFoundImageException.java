package com.kakao.golajuma.vote.domain.exception.vote.image;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundImageException extends BusinessException {
	private static final String MESSAGE = "이미지를 찾을 수 없습니다.";

	public NotFoundImageException() {
		super(MESSAGE, HttpStatus.NOT_FOUND);
	}
}
