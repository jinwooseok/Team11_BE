package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CountOptionDto extends OptionDto {
	private boolean choice;
	private long count;
	private int ratio;

	public CountOptionDto(long id, String name, String image, boolean choice, long count, int ratio) {
		super(id, name, image);
		this.choice = choice;
		this.count = count;
		this.ratio = ratio;
	}

	public static CountOptionDto makeCountOptionDto(
			OptionEntity option, boolean choice, long totalCount) {
		if (totalCount == 0) totalCount = 1;
		return CountOptionDto.builder()
				.id(option.getId())
				.name(option.getOptionName())
				.image(option.getOptionImage())
				.choice(choice)
				.count(option.getOptionCount())
				.ratio(Math.round(option.getOptionCount() / totalCount))
				.build();
	}
}
