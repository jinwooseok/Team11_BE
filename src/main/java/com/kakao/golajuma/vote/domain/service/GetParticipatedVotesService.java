package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
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
		List<VoteEntity> voteEntities = voteRepository.findAllParticipateListByUserId(userId);

		return GetVotesResponse.MyPage.convert(voteEntities);
	}
}
