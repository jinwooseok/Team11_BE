package com.kakao.golajuma.auth.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.request.UpdateUserEmailRequest;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
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
class UpdateUserEmailApiTest {
	@Autowired private ObjectMapper om;

	@Autowired private MockMvc mvc;

	@Autowired private TokenProvider tokenProvider;

	@Autowired UserRepository userRepository;

	private String jwtToken;

	@BeforeEach
	void setup() {
		jwtToken = tokenProvider.createAccessToken(10L);
	}

	@DisplayName("유저는 이메일을 변경하는데 성공한다.")
	@Transactional
	@Test
	void success_update_email_test() throws Exception {
		// given
		UpdateUserEmailRequest requestDto =
				UpdateUserEmailRequest.builder().email("test10@gmail.com").build();
		String requestBody = om.writeValueAsString(requestDto);
		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.patch("/users/email")
								.header("Authorization", "Bearer " + jwtToken)
								.content(requestBody)
								.contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.email").hasJsonPath());
	}

	@DisplayName("이메일이 중복이기 때문에 이메일 업데이트에 실패한다.")
	@Transactional
	@Test
	void duplicate_update_email_test() throws Exception {
		// given
		UserEntity userEntity = userRepository.findById(10L).get();
		UpdateUserEmailRequest requestDto =
				UpdateUserEmailRequest.builder().email(userEntity.getEmail()).build();
		String requestBody = om.writeValueAsString(requestDto);
		// when
		ResultActions resultActions =
				mvc.perform(
						MockMvcRequestBuilders.patch("/users/email")
								.header("Authorization", "Bearer " + jwtToken)
								.content(requestBody)
								.contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
	}
}
