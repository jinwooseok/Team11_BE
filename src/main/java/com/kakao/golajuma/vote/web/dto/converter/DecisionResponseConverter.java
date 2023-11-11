package com.kakao.golajuma.vote.web.dto.converter;

import com.kakao.golajuma.vote.persistence.entity.OptionEntity;
import com.kakao.golajuma.vote.web.dto.response.DecisionResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DecisionResponseConverter {
	private final OptionResultConverter optionResultConverter;

	public DecisionResponse from(Long selectedId, List<OptionEntity> optionsByVote, int totalCount) {
		return DecisionResponse.builder()
				.result(optionResultConverter.from(selectedId, optionsByVote, totalCount))
				.total(totalCount)
				.build();
	}

	public DecisionResponse from(List<OptionEntity> optionsByVote, int totalCount) {
		return DecisionResponse.builder()
				.result(optionResultConverter.from(optionsByVote, totalCount))
				.total(totalCount)
				.build();
	}
}
