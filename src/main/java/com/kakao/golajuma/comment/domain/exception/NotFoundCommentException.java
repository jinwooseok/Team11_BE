package com.kakao.golajuma.comment.domain.exception;

import com.kakao.golajuma.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundCommentException extends BusinessException {
	public NotFoundCommentException() {
		super("댓글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
	}
}
