package com.kakao.golajuma.vote.infra.entity;

import com.kakao.golajuma.vote.domain.exception.CategoryException;
import java.util.Arrays;

public enum Category {
	TOTAL("total"),
	CLOTHES("clothes"),
	FOOD("food");

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
				.orElseThrow(() -> new CategoryException("해당 카테고리는 존재하지 않습니다."));
	}
}
