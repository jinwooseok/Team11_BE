package com.kakao.golajuma.comment.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.kakao.golajuma.comment.domain.service.CommentService;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.SaveCommentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteCommentServiceTest {
	@InjectMocks private CommentService commentService;
	@Mock private CommentRepository commentRepository;

	@Test
	@DisplayName("delete-comment-success")
	void delete_comment_success() {
		// given
		SaveCommentRequest requestDto = SaveCommentRequest.builder().content("삭제테스트").build();
		commentService.create(requestDto, any(), any());
		// when
		commentService.delete(any(), any());
		// then
		verify(requestDto.toEntity(any(), any())).delete();
	}
}
