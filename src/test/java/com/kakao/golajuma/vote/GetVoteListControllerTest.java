package com.kakao.golajuma.vote;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
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

	@Test
	public void getVoteList_test() throws Exception {

		// when
		ResultActions resultActions =
				mvc.perform(
						get("/votes")
								.param("sort", "current")
								.param("active", "continue")
								.param("category", "total"));

		//        resultActions.andExpect(status().isOk());

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
