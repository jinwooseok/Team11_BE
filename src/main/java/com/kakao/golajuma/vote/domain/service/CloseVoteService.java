package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.vote.AlreadyCloseException;
import com.kakao.golajuma.vote.domain.exception.vote.NotFoundVoteException;
import com.kakao.golajuma.vote.domain.exception.vote.NotWriterException;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
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
		// 작성자가 아닌 경우 예외
		if (!voteEntity.isOwner(userId)) {
			throw new NotWriterException();
		}
		// 이미 마감된 경우 예외
		if (!voteEntity.isOn()) {
			throw new AlreadyCloseException();
		}
		voteEntity.close();
	}
}
