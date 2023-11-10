package com.kakao.golajuma.vote.domain.exception.vote.image;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class SaveImageException extends BusinessException {
	private static final String MESSAGE = "이미지 저장 실패";

	public SaveImageException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
