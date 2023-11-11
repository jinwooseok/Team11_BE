package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.common.marker.AbstractResponseDto;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@NoArgsConstructor
@Getter
public class GetVotesResponse implements AbstractResponseDto {

	@Getter
	public static class MainAndFinishPage {
		List<VoteDto> votes;
		Boolean isLast;

		public MainAndFinishPage(List<VoteDto> votes, boolean isLast) {
			this.votes = votes;
			this.isLast = isLast;
		}

		public static MainAndFinishPage convert(List<VoteDto> votes, boolean isLast) {
			return new MainAndFinishPage(votes, isLast);
		}
	}

	@Getter
	public static class MyPage {
		List<VoteListDto> votes;

		public MyPage(List<VoteListDto> votes) {
			this.votes = votes;
		}

		@Builder
		@Getter
		public static class VoteListDto {
			Long id;
			String active;
			String title;
		}

		private static VoteListDto voteToDto(VoteEntity voteEntity) {
			String active = voteEntity.checkActive().getActive();
			return VoteListDto.builder()
					.id(voteEntity.getId())
					.active(active)
					.title(voteEntity.getVoteTitle())
					.build();
		}

		public static MyPage convert(List<VoteEntity> voteEntities) {
			List<VoteListDto> votes = new ArrayList<>();
			for (VoteEntity voteEntity : voteEntities) {
				votes.add(voteToDto(voteEntity));
			}
			return new MyPage(votes);
		}
	}
}
