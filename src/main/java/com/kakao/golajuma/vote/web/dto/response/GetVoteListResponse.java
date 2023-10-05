package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.common.marker.AbstractResponseDto;
import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class GetVoteListResponse implements AbstractResponseDto {

	@Getter
	public static class MainAndFinishPage {
		List<VoteDto> votes = new ArrayList<>();
		Boolean isLast;

		public void isLast(boolean isLast) {
			this.isLast = isLast;
		}

		public void toDto(
				VoteEntity vote,
				boolean on,
				boolean isOwner,
				boolean participate,
				long totalCount,
				List<OptionEntity> options,
				List<Boolean> choiceList) {
			VoteDto voteDto = VoteToDto(vote, isOwner, participate, totalCount);
			this.votes.add(voteDto);
			// case 1 : 질문자, isOwner : true, participate : false, 옵션 카운트 표시
			// case 2 : 응답자 참여 O, isOwner : false, participate : true, 옵션 카운트 표시
			// case 3 : 응답자 참여 X, isOwner : false, participate : false, 옵션 카운트 미표시

			// 투표가 진행되고 있는 상태에서(on) 주인이 아니고, 참여하지 않았을때만 옵션 Count를 보여주지 않음
			if (noParticipateCase(on, isOwner, participate)) {
				voteDto.addOption(options);
			} else {
				voteDto.addCountOption(options, choiceList); // 투표 카운트 표시
			}
		}

		// 투표가 진행되고 있는 상태에서(on) 주인이 아니고, 참여하지 않았을때
		public boolean noParticipateCase(boolean on, boolean isOwner, boolean participate) {
			return on == true && isOwner == false && participate == false;
		}

		@Getter
		@Setter
		@Builder
		public static class VoteDto {
			private long id;
			private Boolean isOwner;
			private long totalCount;
			private LocalDateTime createdDate;
			private LocalDateTime endDate;
			private String active;
			private boolean participate;
			private String title;
			private String content;
			private List<OptionDto> options;

			public void addCountOption(List<OptionEntity> options, List<Boolean> choiceList) {
				this.options = new ArrayList<>();
				for (int i = 0; i < options.size(); i++) {
					this.options.add(toCountOptionDto(options.get(i), choiceList.get(i), totalCount));
				}
			}

			public void addOption(List<OptionEntity> options) {
				for (OptionEntity option : options) {
					this.options.add(toOptionDto(option));
				}
			}
		}

		public VoteDto VoteToDto(
				VoteEntity vote, boolean isOwner, boolean participate, long totalCount) {
			return VoteDto.builder()
					.id(vote.getId())
					.isOwner(isOwner)
					.totalCount(totalCount)
					.createdDate(vote.getCreatedDate())
					.endDate(vote.getVoteEndDate())
					.active(vote.getVoteActive())
					.participate(participate)
					.title(vote.getVoteTitle())
					.content(vote.getVoteContent())
					.build();
		}

		@Builder
		@Getter
		@Setter
		public static class OptionDto {
			private long id;
			private String name;
			private String image;

			public OptionDto(long id, String name, String image) {
				this.id = id;
				this.name = name;
				this.image = image;
			}
		}

		@Getter
		@Setter
		public static class CountOptionDto extends OptionDto {
			private boolean choiced;
			private long count;
			private int ratio;

			public CountOptionDto(
					long id, String name, String image, boolean choiced, long count, int ratio) {
				super(id, name, image);
				this.choiced = choiced;
				this.count = count;
				this.ratio = ratio;
			}
		}

		public static CountOptionDto toCountOptionDto(
				OptionEntity option, boolean choiced, long totalCount) {
			if (totalCount == 0) totalCount = 1;
			return new CountOptionDto(
					option.getId(),
					option.getOptionName(),
					option.getOptionImage(),
					choiced,
					option.getOptionCount(),
					Math.round(option.getOptionCount() / totalCount));
		}

		public static OptionDto toOptionDto(OptionEntity option) {
			return OptionDto.builder()
					.id(option.getId())
					.name(option.getOptionName())
					.image(option.getOptionImage())
					.build();
		}
	}

	@Getter
	public static class MyPage {
		List<VoteDto> votes = new ArrayList<>();

		@Builder
		@Getter
		public static class VoteDto {
			long id;
			String active;
			String title;
		}

		public void toDto(List<VoteEntity> votes) {
			for (VoteEntity vote : votes) {
				this.votes.add(voteToDto(vote));
			}
		}

		public VoteDto voteToDto(VoteEntity vote) {
			return VoteDto.builder()
					.id(vote.getId())
					.active(vote.getVoteActive())
					.title(vote.getVoteTitle())
					.build();
		}
	}
}
