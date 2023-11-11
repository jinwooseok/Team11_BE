package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.vote.AlreadyCloseException;
import com.kakao.golajuma.vote.domain.exception.vote.NotFoundVoteException;
import com.kakao.golajuma.vote.domain.exception.vote.NotWriterException;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CloseVoteService {
	private final VoteRepository voteRepository;

	public void execute(Long voteId, Long userId) {
		VoteEntity voteEntity =
				voteRepository.findById(voteId).orElseThrow(() -> new NotFoundVoteException());
		if (!voteEntity.isOwner(userId)) {
			throw new NotWriterException();
		}
		if (!voteEntity.isOn()) {
			throw new AlreadyCloseException();
		}
		voteEntity.close();
	}
}
