package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.common.marker.AbstractResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GetVoteDetailResponse implements AbstractResponseDto {
	VoteDto voteDto;

	public GetVoteDetailResponse(VoteDto voteDto) {
		this.voteDto = voteDto;
	}
}
