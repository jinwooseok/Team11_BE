package com.kakao.golajuma.vote.web.dto.request;

import com.kakao.golajuma.common.marker.AbstractRequestDto;
import com.kakao.golajuma.vote.util.OptionCheck;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
public class CreateVoteRequest implements AbstractRequestDto {
	@NotBlank(message = "투표 제목은 필수입니다.")
	private String title;

	private String content;

	@OptionCheck List<OptionDto> options;

	private String category;
	private int timeLimit;

	public CreateVoteRequest(
			String voteTitle,
			String category,
			String voteContent,
			int timeLimit,
			List<OptionDto> options) {
		this.title = voteTitle;
		this.category = category;
		this.content = voteContent;
		this.timeLimit = timeLimit;
		this.options = options;
	}

	@Getter
	public static class OptionDto {
		private String name;
		private String image;

		public OptionDto(String name) {
			this.name = name;
			this.image = null;
		}

		public OptionDto(String name, String image) {
			this.name = name;
			this.image = image;
		}

		public String getImage() {
			return this.image;
		}
	}
}
