package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.NullException;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.GetVoteDetailResponse;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetVoteDetailService {

	private final VoteRepository voteRepository;
	private final GetVoteService getVoteService;

	public GetVoteDetailResponse execute(Long voteId, Long userId) {
		// 투표와 옵션리스트 가져오기
		VoteEntity voteEntity =
				voteRepository.findById(voteId).orElseThrow(() -> new NullException("해당 투표가 존재하지 않습니다."));

		VoteDto voteDto = getVoteService.execute(voteEntity, userId);

		return new GetVoteDetailResponse(voteDto);
	}
}
