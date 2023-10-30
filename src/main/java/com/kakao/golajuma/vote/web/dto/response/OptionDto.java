package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.util.ImageUploader;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OptionDto {
	private long id;
	private String optionName;
	private String image;

	public OptionDto(long id, String name, String image) {
		this.id = id;
		this.optionName = name;
		this.image = image;
	}

	public static OptionDto makeOptionDto(OptionEntity option) {
//		String image = ImageUploader.getImage(option.getOptionImage());
		String image = option.getOptionImage();
		return OptionDto.builder()
				.id(option.getId())
				.optionName(option.getOptionName())
				.image(image)
				.build();
	}
}
