package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.GetVotesResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetAskedVotesService {

	private final VoteRepository voteRepository;

	public GetVotesResponse.MyPage execute(Long userId) {
		GetVotesResponse.MyPage responseBody = new GetVotesResponse.MyPage();

		// userId가 올린 투표를 가져옴
		List<VoteEntity> voteList = voteRepository.findAllByUserId(userId);
		responseBody.toDto(voteList);

		return responseBody;
	}
}
