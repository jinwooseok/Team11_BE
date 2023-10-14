package com.kakao.golajuma.comment.api;

import org.junit.jupiter.api.DisplayName;
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
public class DeleteCommentApiTest {
	@Autowired private MockMvc mvc;

	@DisplayName("comment-delete-success-case")
	@Test
	public void deleteTest() throws Exception {
		// given

		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.delete("/votes/1/comments/1")
								.contentType(MediaType.APPLICATION_JSON));

		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").hasJsonPath());
	}
}
