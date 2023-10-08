package com.kakao.golajuma.comment.web.dto.response;

import com.kakao.golajuma.comment.infra.entity.CommentEntity;

public class SaveCommentResponse extends ReadCommentDto {
	public SaveCommentResponse(CommentEntity commentEntity, Boolean isOwner, String username) {
		super(commentEntity, isOwner, username);
	}
}
