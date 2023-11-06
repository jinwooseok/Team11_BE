package com.kakao.golajuma.auth.web.dto.converter;

import com.kakao.golajuma.auth.web.dto.response.TokenResponse;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenConverter {

	public TokenResponse from(
			String accessToken, Date accessExpiredTime, String refreshToken, Date refreshExpiredTime) {
		return TokenResponse.builder()
				.accessToken(accessToken)
				.accessExpiredTime(accessExpiredTime)
				.refreshToken(refreshToken)
				.refreshExpiredTime(refreshExpiredTime)
				.build();
	}
}
