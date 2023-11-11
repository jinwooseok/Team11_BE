package com.kakao.golajuma.vote.web.controller.converter;

import com.kakao.golajuma.vote.domain.service.Sort;
import com.kakao.golajuma.vote.persistence.entity.Active;
import com.kakao.golajuma.vote.persistence.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetVotesRequestConverter {

	public Sort toSort(String sort) {
		return Sort.findSort(sort);
	}

	public Active toActive(String active) {
		return Active.findActive(active);
	}

	public Category toCategory(String category) {
		return Category.findCategory(category);
	}
}
