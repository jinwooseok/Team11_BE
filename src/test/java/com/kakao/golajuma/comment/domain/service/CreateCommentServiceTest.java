package com.kakao.golajuma.comment.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.kakao.golajuma.auth.domain.exception.NotFoundUserException;
import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.comment.persistence.entity.CommentEntity;
import com.kakao.golajuma.comment.persistence.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.CreateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.CreateCommentResponse;
import com.kakao.golajuma.vote.persistence.entity.OptionEntity;
import com.kakao.golajuma.vote.persistence.repository.DecisionRepository;
import com.kakao.golajuma.vote.persistence.repository.OptionRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateCommentServiceTest {
	@InjectMocks private CreateCommentService createCommentService;

	@Mock private CommentRepository commentRepository;

	@Mock private GetUserNameService getUserNameService;

	@Mock private DecisionRepository decisionRepository;

	@Mock private OptionRepository optionRepository;

	@Test
	@DisplayName("유저가 새로운 댓글을 저장하는데 성공한다.")
	void create_comment_success_test() {
		// given
		Long userId = 1L;
		Long voteId = 1L;
		Long commentId = 1L;

		CreateCommentRequest requestDto = CreateCommentRequest.builder().content("contents").build();

		CommentEntity commentEntity =
				CommentEntity.builder()
						.id(commentId)
						.voteId(voteId)
						.userId(userId)
						.content("contents")
						.build();

		UserEntity userEntity = UserEntity.builder().id(1L).nickname("tester").build();

		List<OptionEntity> options = Stream.of(mock(OptionEntity.class)).collect(Collectors.toList());

		when(optionRepository.findAllByVoteId(voteId)).thenReturn(options);

		when(decisionRepository.existsByUserIdAndOptionId(anyLong(), anyLong())).thenReturn(true);

		when(getUserNameService.execute(userId)).thenReturn(userEntity.getNickname());

		when(commentRepository.save(any())).thenReturn(commentEntity);

		// when
		CreateCommentResponse response = createCommentService.execute(requestDto, voteId, userId);
		// then
		verify(commentRepository).save(any(CommentEntity.class));

		assertThat(response.getUsername()).isEqualTo("tester");
		assertThat(response.getContent()).isEqualTo("contents");
		assertThat(response.getIsOwner()).isTrue();
	}

	@DisplayName("유저는 새로운 댓글을 저장하는데 실패한다.")
	@Nested
	class create_comment_fail_case {
		@Test
		@DisplayName("유저 데이터가 존재하지 않기 때문에 실패한다.")
		void not_exist_user_create_test() {
			// given
			Long userId = 99999999L;
			Long voteId = 1L;

			CreateCommentRequest requestDto = CreateCommentRequest.builder().content("contents").build();
			List<OptionEntity> options = Stream.of(mock(OptionEntity.class)).collect(Collectors.toList());

			when(optionRepository.findAllByVoteId(voteId)).thenReturn(options);

			when(decisionRepository.existsByUserIdAndOptionId(anyLong(), anyLong())).thenReturn(true);

			when(getUserNameService.execute(userId)).thenThrow(NotFoundUserException.class);

			// when & then
			assertThrows(
					NotFoundUserException.class,
					() -> createCommentService.execute(requestDto, voteId, userId));
		}
	}
}
