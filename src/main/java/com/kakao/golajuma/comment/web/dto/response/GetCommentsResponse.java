package com.kakao.golajuma.comment.web.dto.response;

import com.kakao.golajuma.common.marker.AbstractResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetCommentsResponse implements AbstractResponseDto {
	private List<CommentDto> comments;
}
