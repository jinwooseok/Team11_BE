package com.kakao.golajuma.auth.web.dto.converter;

import com.kakao.golajuma.auth.web.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenConverter {

	public TokenResponse from(
			String accessToken, Long accessExpiredTime, String refreshToken, Long refreshExpiredTime) {
		return TokenResponse.builder()
				.accessToken(accessToken)
				.accessExpiredTime(accessExpiredTime)
				.refreshToken(refreshToken)
				.refreshExpiredTime(refreshExpiredTime)
				.build();
	}
}
