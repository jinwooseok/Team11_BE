package com.kakao.golajuma.auth.web.interceptor;

import com.kakao.golajuma.auth.domain.token.TokenValidator;
import com.kakao.golajuma.auth.web.support.AuthorizationExtractor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
	private final TokenValidator tokenValidator;

	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (CorsUtils.isPreFlightRequest(request)) {
			return true;
		}

		if (isGetMethod(request)) {
			return true;
		}

		String token = extractToken(request);
		tokenValidator.valid(token);
		return true;
	}

	private boolean isGetMethod(HttpServletRequest request) {
		return request.getMethod().equalsIgnoreCase("GET");
	}

	private String extractToken(HttpServletRequest request) {
		return AuthorizationExtractor.extract(request);
	}
}
