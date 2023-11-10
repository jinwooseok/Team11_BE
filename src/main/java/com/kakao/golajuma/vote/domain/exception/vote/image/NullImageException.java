package com.kakao.golajuma.vote.domain.exception.vote.image;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NullImageException extends BusinessException {
	private static final String MESSAGE = "이미지가 null입니다.";

	public NullImageException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
