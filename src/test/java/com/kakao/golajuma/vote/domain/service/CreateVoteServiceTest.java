package com.kakao.golajuma.vote.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.vote.persistence.entity.Category;
import com.kakao.golajuma.vote.persistence.entity.OptionEntity;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.OptionRepository;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import com.kakao.golajuma.vote.web.dto.response.CreateVoteResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateVoteServiceTest {

	@InjectMocks private CreateVoteService createVoteService;

	@Mock private VoteRepository voteRepository;
	@Mock private OptionRepository optionRepository;

	@DisplayName("투표 생성")
	@Test
	public void createVoteTest() {
		// given
		List<CreateVoteRequest.OptionDto> optionDtoList = new ArrayList<>();
		CreateVoteRequest.OptionDto option1 = new CreateVoteRequest.OptionDto("name");
		CreateVoteRequest.OptionDto option2 = new CreateVoteRequest.OptionDto("name");
		optionDtoList.add(option1);
		optionDtoList.add(option2);
		VoteEntity vote =
				VoteEntity.builder()
						.id(1L)
						.userId(1L)
						.voteTitle("title")
						.voteContent("content")
						.category(Category.TOTAL)
						.build();

		CreateVoteRequest request =
				CreateVoteRequest.builder()
						.title("title")
						.content("content")
						.category("total")
						.options(optionDtoList)
						.timeLimit(60)
						.build();
		Long userId = 1L;

		OptionEntity optionEntity = OptionEntity.builder().build();

		// when
		when(voteRepository.save(any())).thenReturn(vote);
		when(optionRepository.save(any())).thenReturn(optionEntity);

		CreateVoteResponse result = createVoteService.execute(request, userId);
		// then
		assertEquals(1L, result.getId()); // 예상되는 옵션 개수에 따라 수정
	}
}
