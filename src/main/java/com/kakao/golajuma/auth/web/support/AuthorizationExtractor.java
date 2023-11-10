package com.kakao.golajuma.auth.web.support;

import com.kakao.golajuma.auth.domain.exception.AuthenticationTokenNotFoundException;
import com.kakao.golajuma.auth.domain.exception.InvalidTokenFormatException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

public class AuthorizationExtractor {
	private static final String BEARER_TYPE = "Bearer";

	public static String extract(final HttpServletRequest request) {
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (Objects.isNull(authorization)) {
			throw new AuthenticationTokenNotFoundException();
		}
		validateAuthorizationFormat(authorization);
		return authorization.substring(BEARER_TYPE.length());
	}

	private static void validateAuthorizationFormat(String authorization) {
		if (authorization.toLowerCase().startsWith(BEARER_TYPE.toLowerCase())) {
			return;
		}
		throw new InvalidTokenFormatException();
	}
}
