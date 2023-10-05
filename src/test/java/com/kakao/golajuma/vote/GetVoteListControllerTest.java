package com.kakao.golajuma.vote;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class GetVoteListControllerTest {

	@Autowired private ObjectMapper om;
	@Autowired private MockMvc mvc;

	@DisplayName("메인페이지 투표 조회 정상 요청")
	@Test
	public void getVoteList_test() throws Exception {

		// when
		ResultActions resultActions =
				mvc.perform(
						get("/votes")
								.param("idx", "5")
								.param("sort", "current")
								.param("active", "continue")
								.param("category", "total"));
		resultActions.andExpect(status().isOk());
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);
	}

	@DisplayName("완료된 페이지 조회 정상 요청")
	@Test
	public void getVoteList_finishPage_test() throws Exception {

		// when
		ResultActions resultActions =
				mvc.perform(
						get("/votes")
								.param("sort", "current")
								.param("active", "finish")
								.param("category", "total"));
		resultActions.andExpect(status().isOk());
		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);
	}

	@Test
	public void getVoteListInMyPageByAsk_test() throws Exception {

		// when
		ResultActions resultActions = mvc.perform(get("/users/votes/ask"));

		//        resultActions.andExpect(status().isOk());

		// eye
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		System.out.println("테스트 : " + responseBody);
	}
}
