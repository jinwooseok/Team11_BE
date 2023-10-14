package com.kakao.golajuma.comment.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.golajuma.comment.web.dto.request.SaveCommentRequest;
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
public class UpdateCommentApiTest {
	@Autowired private ObjectMapper om;

	@Autowired private MockMvc mvc;

	@DisplayName("comment-update-success-test")
	@Test
	public void updateTest() throws Exception {
		// given
		SaveCommentRequest requestDto = SaveCommentRequest.builder().content("메롱이다.2222").build();
		String requestBody = om.writeValueAsString(requestDto);

		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.patch("/votes/1/comments/1")
								.content(requestBody)
								.contentType(MediaType.APPLICATION_JSON));

		// then
		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.id").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.isOwner").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.username").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.content").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").hasJsonPath());
	}
}
