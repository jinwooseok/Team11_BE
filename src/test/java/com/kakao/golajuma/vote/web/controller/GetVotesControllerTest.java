package com.kakao.golajuma.vote.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class GetVotesControllerTest {

	@Autowired private MockMvc mvc;
	@Autowired private UserRepository userRepository;
	@Autowired private TokenProvider tokenProvider;
	private String jwtToken;

	@BeforeEach
	public void setup() throws Exception {
		jwtToken = tokenProvider.createAccessToken(1L);
		UserEntity user =
				UserEntity.builder()
						.id(1L)
						.nickname("test")
						.email("test@gmail.com")
						.password("1234")
						.build();
		userRepository.save(user);
	}

	@DisplayName("메인페이지 투표 조회 정상 요청")
	@Test
	public void mainPage_test() throws Exception {
		// given
		String page = "0";

		// when
		ResultActions resultActions =
				mvc.perform(
						get("/votes")
								.header("Authorization", "Bearer " + jwtToken)
								.param("page", page)
								.param("sort", "current")
								.param("active", "continue")
								.param("category", "total"));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").hasJsonPath())
				.andExpect(jsonPath("$.data.votes").isArray())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("완료된 페이지 조회 정상 요청")
	@Test
	public void completePage_test() throws Exception {
		// given
		String page = "0";

		// when
		ResultActions resultActions =
				mvc.perform(
						get("/votes")
								.header("Authorization", "Bearer " + jwtToken)
								.param("page", page)
								.param("sort", "popular")
								.param("active", "complete")
								.param("category", "total"));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").hasJsonPath())
				.andExpect(jsonPath("$.data.votes").isArray())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("투표 리스트 조회 에러 - sort")
	@Test
	public void error_test_active() throws Exception {
		// given
		String sort = "error";
		String active = "continue";
		String category = "total";

		// when
		ResultActions resultActions =
				mvc.perform(
						get("/votes")
								.header("Authorization", "Bearer " + jwtToken)
								.param("sort", sort)
								.param("active", active)
								.param("category", category));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("투표 리스트 조회 에러 - active")
	@Test
	public void error_test_sort() throws Exception {
		// given
		String sort = "current";
		String active = "error";
		String category = "total";

		// when
		ResultActions resultActions =
				mvc.perform(
						get("/votes")
								.header("Authorization", "Bearer " + jwtToken)
								.param("sort", sort)
								.param("active", active)
								.param("category", category));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("투표 리스트 조회 에러 - category")
	@Test
	public void error_test_category() throws Exception {
		// given
		String sort = "current";
		String active = "continue";
		String category = "error";

		// when
		ResultActions resultActions =
				mvc.perform(
						get("/votes")
								.header("Authorization", "Bearer " + jwtToken)
								.param("sort", sort)
								.param("active", active)
								.param("category", category));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("마이페이지 내가한 질문 리스트 조회 정상 요청")
	@Test
	public void myPageByAsk_test() throws Exception {

		// when
		ResultActions resultActions =
				mvc.perform(get("/users/votes/ask").header("Authorization", "Bearer " + jwtToken));

		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").hasJsonPath())
				.andExpect(jsonPath("$.data.votes").isArray())
				.andExpect(jsonPath("$.data.votes[0].id").hasJsonPath())
				.andExpect(jsonPath("$.data.votes[0].title").hasJsonPath())
				.andExpect(jsonPath("$.data.votes[0].active").hasJsonPath());
	}

	@DisplayName("마이페이지 내가한 참여한 질문 리스트 조회 정상 요청")
	@Test
	public void myPageByParticipate_test() throws Exception {

		// when
		ResultActions resultActions =
				mvc.perform(get("/users/votes/participate").header("Authorization", "Bearer " + jwtToken));

		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").hasJsonPath())
				.andExpect(jsonPath("$.data.votes").isArray())
				.andExpect(jsonPath("$.data.votes[0].id").hasJsonPath())
				.andExpect(jsonPath("$.data.votes[0].title").hasJsonPath())
				.andExpect(jsonPath("$.data.votes[0].active").hasJsonPath());
	}
}
