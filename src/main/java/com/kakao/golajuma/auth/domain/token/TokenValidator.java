package com.kakao.golajuma.auth.domain.token;

import com.kakao.golajuma.auth.domain.exception.TokenExpiredException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenValidator {
	private final TokenResolver tokenResolver;

	public void valid(String token) {
		Date expiredDate = tokenResolver.getExpiredDate(token);

		if (expiredDate.before(new Date())) {
			throw new TokenExpiredException();
		}
	}
}
