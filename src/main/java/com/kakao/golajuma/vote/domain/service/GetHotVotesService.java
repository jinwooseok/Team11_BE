package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.GetVotesResponse;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetHotVotesService {
	private final GetVoteService getVoteService;

	private final VoteRepository voteRepository;

	public GetVotesResponse.MainAndFinishPage execute(final Long userId, final int page) {
		final int SIZE = 5;

		List<LocalDateTime> lastTime = findLastTime();

		Pageable pageable = PageRequest.of(page, SIZE);

		Slice<VoteEntity> voteEntities =
				voteRepository.findByTimeLimitAndDecisionCount(lastTime.get(0), lastTime.get(1), pageable);

		List<VoteDto> voteDtoList = voteDtoConverter(voteEntities, userId);

		boolean isLast = voteEntities.isLast();

		return new GetVotesResponse.MainAndFinishPage(voteDtoList, isLast);
	}

	private List<LocalDateTime> findLastTime() {
		LocalDateTime startTime =
				LocalDateTime.now().minusHours(1).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime endTime = startTime.plusHours(1);
		List<LocalDateTime> timeSet = new ArrayList<>();
		timeSet.add(startTime);
		timeSet.add(endTime);
		return timeSet;
	}

	private List<VoteDto> voteDtoConverter(Slice<VoteEntity> voteEntitySlice, Long userId) {
		List<VoteDto> voteDtoList;
		voteDtoList =
				voteEntitySlice.stream()
						.map(voteEntity -> getVoteService.execute(voteEntity, userId))
						.collect(Collectors.toList());
		return voteDtoList;
	}
}
