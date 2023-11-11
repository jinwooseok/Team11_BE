package com.kakao.golajuma.vote.persistence.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.golajuma.vote.persistence.entity.DecisionEntity;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@Import(ObjectMapper.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GetHotVoteListRepositoryTest {

	@Autowired private VoteRepository voteRepository;

	@Autowired DecisionRepository decisionRepository;

	@Test
	@Transactional
	void hotVote_findByTimeLimitAndDecisionCount_test() {
		// given
		DecisionEntity decisionEntity =
				DecisionEntity.builder()
						.userId(1L)
						.optionId(1L)
						.createdDate(LocalDateTime.now())
						.updatedDate(LocalDateTime.now())
						.deleted(false)
						.build();
		decisionRepository.save(decisionEntity);

		LocalDateTime startTime = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
		LocalDateTime endTime = startTime.plusHours(1);

		// when
		Slice<VoteEntity> voteEntitySlice =
				voteRepository.findByTimeLimitAndDecisionCount(startTime, endTime, Pageable.unpaged());
		// then
		assertThat(voteEntitySlice.toList().get(0).getClass()).isEqualTo(VoteEntity.class);
	}
}
