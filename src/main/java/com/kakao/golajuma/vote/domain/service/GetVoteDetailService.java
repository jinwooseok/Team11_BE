package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.NullException;
import com.kakao.golajuma.vote.infra.entity.Active;
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

	private final VoteRepository voteJPARepository;
	private final GetVoteService getVoteService;

	public GetVoteDetailResponse getVoteDetail(long voteId, long userId) {
		// 투표와 옵션리스트 가져오기
		VoteEntity vote = voteJPARepository.findById(voteId);

		if (vote == null) {
			throw new NullException("투표가 존재하지 않습니다.");
		}

		// 투표 진행 상태
		boolean on = checkActive(vote);

		VoteDto voteDto = getVoteService.getVote(vote, userId, on);

		return new GetVoteDetailResponse(voteDto);
	}

	public boolean checkActive(VoteEntity vote) {
		if (vote.checkActive() == Active.CONTINUE) {
			return true;
		}
		return false;
	}
}
