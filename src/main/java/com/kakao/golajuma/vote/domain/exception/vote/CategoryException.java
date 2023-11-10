package com.kakao.golajuma.vote.domain.exception.vote;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CategoryException extends BusinessException {
	private static final String MESSAGE = "잘못된 카테고리 요청입니다. category";

	public CategoryException() {
		super(MESSAGE, HttpStatus.BAD_REQUEST);
	}
}
