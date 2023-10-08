package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.common.marker.AbstractResponseDto;
import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VoteDto implements AbstractResponseDto {
	private long id;
	private String username;
	private Boolean isOwner;
	private long totalCount;
	private LocalDateTime createdDate;
	private LocalDateTime endDate;
	private String active;
	private boolean participate;
	private String title;
	private String content;
	private List<OptionDto> options = new ArrayList<>();

	public VoteDto(VoteEntity vote, String username, boolean isOwner, boolean participate) {
		this.id = vote.getId();
		this.username = username;
		this.isOwner = isOwner;
		this.totalCount = vote.getVoteTotalCount();
		this.createdDate = vote.getCreatedDate();
		this.endDate = vote.getVoteEndDate();
		this.active = vote.getVoteActive();
		this.participate = participate;
		this.title = vote.getVoteTitle();
		this.content = vote.getVoteContent();
	}

	public static VoteDto makeDto(
			VoteEntity vote,
			boolean on,
			String username,
			boolean isOwner,
			boolean participate,
			List<OptionEntity> options,
			List<Boolean> choiceList) {
		VoteDto voteDto = new VoteDto(vote, username, isOwner, participate);

		// case 1 : 질문자, isOwner : true, participate : false, 옵션 카운트 표시
		// case 2 : 응답자, 참여 O, isOwner : false, participate : true, 옵션 카운트 표시
		// case 3 : 응답자, 참여 X, isOwner : false, participate : false, 옵션 카운트 미표시
		// 투표가 진행되고 있는 상태에서(on) 주인이 아니고, 참여하지 않았을때만 옵션 Count를 보여주지 않음
		if (noParticipateCase(on, isOwner, participate)) {
			voteDto.addOption(options);
		} else {
			voteDto.addCountOption(options, choiceList); // 투표 카운트 표시
		}

		return voteDto;
	}

	// 투표가 진행되고 있는 상태에서(on) 주인이 아니고, 참여하지 않았을때
	public static boolean noParticipateCase(boolean on, boolean isOwner, boolean participate) {
		return on == true && isOwner == false && participate == false;
	}

	public void addOption(List<OptionEntity> options) {
		for (OptionEntity option : options) {
			this.options.add(toOptionDto(option));
		}
	}

	public void addCountOption(List<OptionEntity> options, List<Boolean> choiceList) {
		for (int i = 0; i < options.size(); i++) {
			this.options.add(toCountOptionDto(options.get(i), choiceList.get(i), totalCount));
		}
	}

	@Builder
	@Getter
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

	public static OptionDto toOptionDto(OptionEntity option) {
		return OptionDto.builder()
				.id(option.getId())
				.name(option.getOptionName())
				.image(option.getOptionImage())
				.build();
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
}
