package com.kakao.golajuma.vote.domain.event;

import com.kakao.golajuma.comment.domain.service.DeleteAllCommentByVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class DeleteAllCommentEventHandler {
	private final DeleteAllCommentByVoteService deleteAllCommentByVoteService;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteComment(DeletedDecisionEvent event) {
		deleteAllCommentByVoteService.execute(event.getVoteId(), event.getUserId());
	}
}
