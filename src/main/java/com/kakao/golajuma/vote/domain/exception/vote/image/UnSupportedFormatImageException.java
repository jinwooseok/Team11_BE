package com.kakao.golajuma.vote.domain.exception.vote.image;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UnSupportedFormatImageException extends BusinessException {
	private static final String MESSAGE = "지원하지 않는 이미지 형식입니다.";

	public UnSupportedFormatImageException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
