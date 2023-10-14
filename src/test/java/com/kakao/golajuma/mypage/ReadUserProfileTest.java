package com.kakao.golajuma.mypage;

import com.kakao.golajuma.auth.domain.token.TokenProvider;
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
public class ReadUserProfileTest {
	@Autowired private MockMvc mvc;
	@Autowired private TokenProvider tokenProvider;
	private String jwtToken;

	@DisplayName("profile-read-success-case")
	@Test
	public void readTest() throws Exception {
		// given
		jwtToken = tokenProvider.createAccessToken(1L);
		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.get("/mypage").header("Authorization", "Bearer " + jwtToken));

		String responseBody = resultActions.andReturn().getResponse().getContentAsString();

		System.out.println("테스트 : " + responseBody);
		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.nickName").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.email").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.image").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.createVoteCount").hasJsonPath())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.participateVoteCount").hasJsonPath());
	}
}
