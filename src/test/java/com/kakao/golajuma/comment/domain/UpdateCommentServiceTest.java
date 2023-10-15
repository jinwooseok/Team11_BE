package com.kakao.golajuma.comment.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.kakao.golajuma.comment.domain.service.CommentService;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.web.dto.request.UpdateCommentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class UpdateCommentServiceTest {
	@InjectMocks private CommentService commentService;

	@Test
	@DisplayName("update-comment-success")
	void update_comment_success() {
		// given
		UpdateCommentRequest requestDto = UpdateCommentRequest.builder().content("업데이트 테스트").build();
		CommentEntity commentEntity = CommentEntity.builder().build();
		// when
		commentService.update(requestDto, any(), any());
		verify(commentEntity).updateContent(any());
	}
}
