package com.kakao.golajuma.comment.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.kakao.golajuma.comment.domain.service.CommentService;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReadCommentServiceTest {
	@InjectMocks private CommentService commentService;
	@Mock private CommentRepository commentRepository;

	@Test
	@DisplayName("create-comment-success")
	void read_comment_success() {
		// when
		commentService.readList(any(), any());
		verify(commentRepository).findByVoteId(any());
	}
}
