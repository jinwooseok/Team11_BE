package com.kakao.golajuma.vote.domain.exception.vote.image;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ImageSizeException extends BusinessException {
	private static final String MESSAGE = "이미지 크기가 너무 큽니다.";

	public ImageSizeException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
