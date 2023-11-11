package com.kakao.golajuma.vote.domain.event;

import lombok.Getter;

@Getter
public class DeletedDecisionEvent {
	private final Long voteId;
	private final Long userId;

	private DeletedDecisionEvent(Long voteId, Long userId) {
		this.voteId = voteId;
		this.userId = userId;
	}

	public static DeletedDecisionEvent of(Long voteId, Long userId) {
		return new DeletedDecisionEvent(voteId, userId);
	}
}
