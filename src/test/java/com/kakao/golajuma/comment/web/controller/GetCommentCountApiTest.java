package com.kakao.golajuma.comment.web.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class GetCommentCountApiTest {
	@Autowired private MockMvc mvc;

	@DisplayName("유저는 해당 댓글의 총 개수를 호출하는데 성공한다.")
	@Test
	void get_comment_count_success() throws Exception {
		Long voteId = 1L;
		// when
		ResultActions resultActions =
				mvc.perform(MockMvcRequestBuilders.get("/votes/" + voteId + "/comments/count"));
		// then
		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.commentCount").hasJsonPath());
	}
}
