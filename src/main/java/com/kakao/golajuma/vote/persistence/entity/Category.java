package com.kakao.golajuma.vote.persistence.entity;

import com.kakao.golajuma.vote.domain.exception.vote.CategoryException;
import java.util.Arrays;

public enum Category {
	TOTAL("total"),
	BUY("buy"),
	WHERE("where"),
	WHAT("what"),
	FOOD("food"),
	MOVIE("movie"),
	LISTEN("listen"),
	ETC("etc");

	private final String category;

	Category(String category) {
		this.category = category;
	}

	public String getCategory() {
		return this.category;
	}

	public static Category findCategory(String category) {
		return Arrays.stream(Category.values())
				.filter(Category -> Category.getCategory().equals(category))
				.findAny()
				.orElseThrow(() -> new CategoryException());
	}

	public static boolean isTotalRequest(Category category) {
		return category == Category.TOTAL;
	}
}
