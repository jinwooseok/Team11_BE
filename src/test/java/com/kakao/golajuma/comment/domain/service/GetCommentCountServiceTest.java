package com.kakao.golajuma.comment.domain.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.comment.persistence.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.response.GetCommentCountResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetCommentCountServiceTest {

	@InjectMocks private GetCommentCountService getCommentCountService;

	@Mock private CommentRepository commentRepository;

	@Test
	@DisplayName("유저가 댓글 리스트를 호출하는데 성공한다.")
	void get_comment_list_success_test() {
		// given
		Long voteId = 1L;
		when(commentRepository.countByVoteId(voteId)).thenReturn(10);
		// when
		GetCommentCountResponse response = getCommentCountService.execute(voteId);
		// then
		assertThat(response.getCommentCount()).isEqualTo(10);
	}
}
