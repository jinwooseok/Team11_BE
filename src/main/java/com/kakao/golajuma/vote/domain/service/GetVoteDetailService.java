package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.vote.NotFoundVoteException;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
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
		VoteEntity voteEntity =
				voteRepository.findById(voteId).orElseThrow(() -> new NotFoundVoteException());

		VoteDto voteDto = getVoteService.execute(voteEntity, userId);

		return GetVoteDetailResponse.convert(voteDto);
	}
}
