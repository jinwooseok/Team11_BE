package com.kakao.golajuma.vote.web.dto.converter;

import com.kakao.golajuma.vote.persistence.entity.OptionEntity;
import com.kakao.golajuma.vote.util.OptionDivideUtil;
import com.kakao.golajuma.vote.web.dto.response.OptionResult;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OptionResultConverter {

	public List<OptionResult> from(
			Long selectedId, List<OptionEntity> optionsByVote, int totalCount) {
		return optionsByVote.stream()
				.map(
						entity ->
								OptionResult.builder()
										.id(entity.getId())
										.optionCount((int) entity.getOptionCount())
										.optionRatio(OptionDivideUtil.getRatio(entity.getOptionCount(), totalCount))
										.choice(isSelected(entity.getId(), selectedId))
										.build())
				.collect(Collectors.toList());
	}

	public List<OptionResult> from(List<OptionEntity> optionsByVote, int totalCount) {
		return optionsByVote.stream()
				.map(
						entity ->
								OptionResult.builder()
										.id(entity.getId())
										.optionCount((int) entity.getOptionCount())
										.optionRatio(OptionDivideUtil.getRatio(entity.getOptionCount(), totalCount))
										.choice(false)
										.build())
				.collect(Collectors.toList());
	}

	private boolean isSelected(Long optionId, Long selectedId) {
		return optionId.equals(selectedId);
	}
}
