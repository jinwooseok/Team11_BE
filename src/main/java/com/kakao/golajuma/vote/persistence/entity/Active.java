package com.kakao.golajuma.vote.persistence.entity;

import com.kakao.golajuma.vote.domain.exception.vote.ActiveException;
import java.util.Arrays;

public enum Active {
	CONTINUE("continue"),
	COMPLETE("complete");

	private final String active;

	Active(String active) {
		this.active = active;
	}

	public String getActive() {
		return this.active;
	}

	public static Active findActive(String active) {
		return Arrays.stream(Active.values())
				.filter(Active -> Active.getActive().equals(active))
				.findAny()
				.orElseThrow(() -> new ActiveException());
	}

	public static boolean isContinueRequest(Active active) {
		return active == Active.CONTINUE;
	}
}
