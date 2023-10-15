package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.OptionRepository;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import com.kakao.golajuma.vote.web.dto.response.CreateVoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CreateVoteService {

	private final VoteRepository voteJPARepository;
	private final OptionRepository optionJPARepository;

	@Transactional
	public CreateVoteResponse createVote(CreateVoteRequest request, Long userId) {
		VoteEntity vote = VoteEntity.createEntity(request, userId);
		voteJPARepository.save(vote);
		long voteId = vote.getId();
		for (CreateVoteRequest.OptionDTO optionDto : request.getOptions()) {
			OptionEntity option = OptionEntity.createEntity(optionDto, voteId);
			optionJPARepository.save(option);
		}
		return new CreateVoteResponse(voteId);
	}
}
