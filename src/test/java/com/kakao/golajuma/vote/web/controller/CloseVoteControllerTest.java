package com.kakao.golajuma.vote.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import com.kakao.golajuma.vote.persistence.entity.Category;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CloseVoteControllerTest {

	@Autowired private MockMvc mvc;
	@Autowired private UserRepository userRepository;
	@Autowired private TokenProvider tokenProvider;
	@Autowired VoteRepository voteRepository;
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

	@DisplayName("투표 마감 성공")
	@Test
	@Transactional
	public void closeVote_test() throws Exception {
		// given
		LocalDateTime date = LocalDateTime.now().plusMinutes(240);
		VoteEntity vote =
				VoteEntity.builder()
						.voteTitle("title")
						.category(Category.TOTAL)
						.userId(1L)
						.voteEndDate(date)
						.build();

		Long voteId = voteRepository.save(vote).getId();

		// when
		ResultActions resultActions =
				mvc.perform(
						patch("/votes/" + voteId + "/close").header("Authorization", "Bearer " + jwtToken));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").hasJsonPath())
				.andExpect(jsonPath("$.data").hasJsonPath());
	}

	@DisplayName("투표 마감 실패 - 존재하지 않는 투표")
	@Test
	public void closeVote_error_test_notFoundVote() throws Exception {
		// given
		Long voteId = -1L;

		// when
		ResultActions resultActions =
				mvc.perform(
						patch("/votes/" + voteId + "/close").header("Authorization", "Bearer " + jwtToken));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}

	@DisplayName("투표 마감 실패 - 이미 마감된 투표")
	@Test
	public void closeVote_error_test_alreadyComplete() throws Exception {
		// given
		LocalDateTime date = LocalDateTime.now().minusMinutes(240);
		VoteEntity vote =
				VoteEntity.builder()
						.voteTitle("title")
						.category(Category.TOTAL)
						.userId(1L)
						.voteEndDate(date)
						.build();

		Long voteId = voteRepository.save(vote).getId();

		// when
		ResultActions resultActions =
				mvc.perform(
						patch("/votes/" + voteId + "/close").header("Authorization", "Bearer " + jwtToken));
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);

		// then
		resultActions
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").hasJsonPath());
	}
}
