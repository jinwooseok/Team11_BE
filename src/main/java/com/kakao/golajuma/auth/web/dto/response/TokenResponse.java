package com.kakao.golajuma.auth.web.dto.response;

import com.kakao.golajuma.common.marker.AbstractResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TokenResponse implements AbstractResponseDto {

	private String accessToken;
	private Long accessExpiredTime;
	private String refreshToken;
	private Long refreshExpiredTime;
}
