package com.kakao.golajuma.comment.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.comment.domain.exception.NotFoundCommentException;
import com.kakao.golajuma.comment.persistence.entity.CommentEntity;
import com.kakao.golajuma.comment.persistence.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.UpdateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.UpdateCommentResponse;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateCommentServiceTest {
	@InjectMocks private UpdateCommentService updateCommentService;

	@Mock private CommentRepository commentRepository;

	@Mock private GetUserNameService getUserNameService;

	@Test
	@DisplayName("유저가 댓글을 업데이트를 하는데 성공한다.")
	void create_comment_success_test() {
		// given
		Long userId = 1L;
		Long commentId = 1L;
		Long voteId = 1L;

		UpdateCommentRequest requestDto = UpdateCommentRequest.builder().content("new content").build();

		UserEntity userEntity = UserEntity.builder().id(1L).nickname("tester").build();

		CommentEntity commentEntity =
				CommentEntity.builder()
						.id(commentId)
						.voteId(voteId)
						.userId(userId)
						.content("old content")
						.build();

		when(commentRepository.findByCommentIdUserId(commentId, userId))
				.thenReturn(Optional.of(commentEntity));
		when(getUserNameService.execute(userId)).thenReturn(userEntity.getNickname());
		// when
		UpdateCommentResponse response = updateCommentService.execute(requestDto, commentId, userId);
		// then
		assertThat(response.getUsername()).isEqualTo("tester");
		assertThat(response.getContent()).isEqualTo("new content");
		assertThat(response.getIsOwner()).isTrue();
	}

	@DisplayName("유저는 댓글을 업데이트 하는 것에 실패한다.")
	@Nested
	class update_comment_fail_case {
		@Test
		@DisplayName("없는 댓글을 요청했기 때문에 실패한다.")
		void not_exist_comment_update_test() {
			// given
			Long commentId = 99999999L;
			Long userId = 1L;

			UpdateCommentRequest requestDto =
					UpdateCommentRequest.builder().content("new content").build();

			when(commentRepository.findByCommentIdUserId(commentId, userId))
					.thenThrow(NotFoundCommentException.class);
			// when & then
			assertThrows(
					NotFoundCommentException.class,
					() -> updateCommentService.execute(requestDto, commentId, userId));
		}

		@Test
		@DisplayName("본인이 작성하지 않은 댓글 업데이트를 요청했기 때문에 실패한다.")
		void no_owner_update_test() {
			// given
			Long commentId = 1L;
			Long wrongUserId = 2L;

			UpdateCommentRequest requestDto =
					UpdateCommentRequest.builder().content("new content").build();

			when(commentRepository.findByCommentIdUserId(commentId, wrongUserId))
					.thenThrow(NotFoundCommentException.class);
			// when & then
			assertThrows(
					NotFoundCommentException.class,
					() -> updateCommentService.execute(requestDto, commentId, wrongUserId));
		}
	}
}
