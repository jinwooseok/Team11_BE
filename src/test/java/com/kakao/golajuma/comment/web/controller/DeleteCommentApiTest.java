package com.kakao.golajuma.comment.web.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.comment.persistence.entity.CommentEntity;
import com.kakao.golajuma.comment.persistence.repository.CommentRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class DeleteCommentApiTest {
	@Autowired private MockMvc mvc;
	@Autowired private TokenProvider tokenProvider;
	@Autowired private CommentRepository commentRepository;
	private String jwtToken;

	@BeforeEach
	void setUp() {
		// given
		jwtToken = tokenProvider.createAccessToken(1L);
	}

	@DisplayName("유저는 댓글을 삭제하는데 성공한다.")
	@Transactional
	@Test
	void delete_comment_success_tes() throws Exception {
		// given
		CommentEntity commentEntity = commentRepository.findByUserId(1L).stream().findFirst().get();
		Long voteId = commentEntity.getVoteId();
		Long commentId = commentEntity.getId();
		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.delete("/votes/" + voteId + "/comments/" + commentId)
								.header("Authorization", "Bearer " + jwtToken)
								.contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("유저는 댓글을 삭제하는데 실패한다.")
	@Nested
	class delete_comment_fail_case {
		@DisplayName("본인에게 존재하지 않은 댓글을 삭제하기 때문에 실패한다.")
		@Transactional
		@Test
		void not_owner_delete_comment_fail_test() throws Exception {
			// given
			CommentEntity commentEntity = commentRepository.findByUserId(2L).stream().findFirst().get();
			Long voteId = commentEntity.getVoteId();
			Long commentId = commentEntity.getId();

			// when
			ResultActions resultActions =
					mvc.perform(
							MockMvcRequestBuilders.delete("/votes/" + voteId + "/comments/" + commentId)
									.header("Authorization", "Bearer " + jwtToken)
									.contentType(MediaType.APPLICATION_JSON));
			// then
			resultActions.andExpect(status().isNotFound());
		}

		@DisplayName("없는 댓글을 삭제하기 때문에 실패한다.")
		@Transactional
		@Test
		void not_exist_comment_delete_fail_test() throws Exception {
			// given
			CommentEntity commentEntity = commentRepository.findByUserId(1L).stream().findFirst().get();
			Long voteId = commentEntity.getVoteId();
			Long commentId = 999999L;
			// when
			ResultActions resultActions =
					mvc.perform(
							MockMvcRequestBuilders.delete("/votes/" + voteId + "/comments/" + commentId)
									.header("Authorization", "Bearer " + jwtToken)
									.contentType(MediaType.APPLICATION_JSON));
			// then
			resultActions.andExpect(status().isNotFound());
		}
	}
}
