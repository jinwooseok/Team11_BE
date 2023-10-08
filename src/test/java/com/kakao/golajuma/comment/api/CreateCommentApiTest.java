package com.kakao.golajuma.comment.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class CreateCommentApiTest {
	@Autowired private ObjectMapper om;

	@Autowired private MockMvc mvc;

	@DisplayName("comment-create-success-case")
	@Test
	public void createTest() throws Exception {
		// given
		SaveCommentRequest requestDto = SaveCommentRequest.builder().content("메롱이다.").build();
		String requestBody = om.writeValueAsString(requestDto);
		System.out.println("테스트 : " + requestBody);
		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.post("/votes/1/comments")
								.header(
										"Authorization",
										"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjMsImlhdCI6MTY5Njc1MDU0OCwiZXhwIjoxNjk2NzUyMzQ4fQ.sKzZNz2nlvFX7a6P5Z3sGh2MpTQarvpZ-TR2qoh5NQw")
								.content(requestBody)
								.contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().is2xxSuccessful());
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();

		System.out.println("테스트 : " + responseBody);

		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.isOwner").value(true))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("asdf"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.content").value("메롱이다."))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("생성 성공"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("200"));
	}
}
