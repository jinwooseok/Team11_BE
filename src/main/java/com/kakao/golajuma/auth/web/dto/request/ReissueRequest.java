package com.kakao.golajuma.auth.web.dto.request;

import com.kakao.golajuma.common.marker.AbstractRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReissueRequest implements AbstractRequestDto {
	private String refreshToken;
}
