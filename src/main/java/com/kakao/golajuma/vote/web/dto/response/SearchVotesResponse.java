package com.kakao.golajuma.vote.web.dto.response;

import com.kakao.golajuma.common.marker.AbstractResponseDto;
import java.util.List;
import lombok.Getter;

@Getter
public class SearchVotesResponse implements AbstractResponseDto {
	List<VoteDto> votes;
	Boolean isLast;

	public SearchVotesResponse(List<VoteDto> votes, boolean isLast) {
		this.votes = votes;
		this.isLast = isLast;
	}

	public static SearchVotesResponse convert(List<VoteDto> votes, boolean isLast) {
		return new SearchVotesResponse(votes, isLast);
	}
}
