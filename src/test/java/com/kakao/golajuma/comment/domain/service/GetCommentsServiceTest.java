package com.kakao.golajuma.comment.domain.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.comment.persistence.entity.CommentEntity;
import com.kakao.golajuma.comment.persistence.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.response.CommentDto;
import com.kakao.golajuma.comment.web.dto.response.GetCommentsResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetCommentsServiceTest {
	@InjectMocks private GetCommentsService getCommentsService;

	@Mock private CommentRepository commentRepository;

	@Mock private GetUserNameService getUserNameService;

	@Test
	@DisplayName("유저가 댓글 리스트를 호출하는데 성공한다.")
	void get_comment_list_success_test() {
		// given
		Long userId = 1L;
		Long voteId = 1L;

		CommentEntity commentEntity1 =
				CommentEntity.builder().voteId(voteId).userId(userId).content("content1").build();
		CommentEntity commentEntity2 =
				CommentEntity.builder().voteId(voteId).userId(userId).content("content2").build();

		List<CommentEntity> commentEntities =
				Stream.of(commentEntity1, commentEntity2).collect(Collectors.toList());

		when(commentRepository.findByVoteId(voteId)).thenReturn(commentEntities);
		when(getUserNameService.execute(userId)).thenReturn("tester");
		// when
		GetCommentsResponse response = getCommentsService.execute(voteId, userId);
		// then
		assertThat(response.getComments().get(0).getClass()).isEqualTo(CommentDto.class);

		assertThat(response.getComments().get(0).getContent()).isEqualTo("content1");
		assertThat(response.getComments().get(1).getContent()).isEqualTo("content2");
	}
}
