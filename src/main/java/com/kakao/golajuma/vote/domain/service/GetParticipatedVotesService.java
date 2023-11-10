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
public class GetParticipatedVotesService {

	private final VoteRepository voteRepository;

	public GetVotesResponse.MyPage execute(Long userId) {
		// userId가 투표한 투표 리스트를 decision 레포에서 찾아야함
		List<VoteEntity> voteEntities = voteRepository.findAllParticipateListByUserId(userId);

		return GetVotesResponse.MyPage.convert(voteEntities);
	}
}
