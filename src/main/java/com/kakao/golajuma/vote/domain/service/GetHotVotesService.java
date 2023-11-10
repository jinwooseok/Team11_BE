package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.HotVoteRepository;
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

	private final HotVoteRepository hotVoteRepository;

	public GetVotesResponse.MainAndFinishPage execute(final Long userId, final int page) {
		int size = 5;
		int beforeHour = 1;
		int interval = 1;
		// 핫게시판에 반영할 1시간전을 계산
		List<LocalDateTime> lastTime = findLastTime(beforeHour, interval);
		// 어디서부터 몇개씩 가져올건지
		Pageable pageable = PageRequest.of(page, size);
		// repository를 통해 찾음
		Slice<VoteEntity> voteEntities =
				hotVoteRepository.findByTimeLimitAndDecisionCount(
						lastTime.get(0), lastTime.get(1), pageable);
		// entity list >> dto list
		List<VoteDto> voteDtoList = voteDtoConverter(voteEntities, userId);
		// 마지막 페이지인지 검사
		boolean isLast = voteEntities.isLast();
		// responseDto로 변환 후 return
		return new GetVotesResponse.MainAndFinishPage(voteDtoList, isLast);
	}

	private List<LocalDateTime> findLastTime(int before, int interval) {
		LocalDateTime startTime =
				LocalDateTime.now().minusHours(before).withMinute(0).withSecond(0).withNano(0);
		LocalDateTime endTime = startTime.plusHours(interval);
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
