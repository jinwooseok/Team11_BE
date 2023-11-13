package com.kakao.golajuma.auth.web.support;

import com.kakao.golajuma.auth.domain.exception.AuthorizationException;
import com.kakao.golajuma.auth.domain.token.TokenResolver;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {
	private static final Long NONE_MEMBER = 0L;
	private final TokenResolver tokenResolver;

	@Qualifier("auth")
	private final TokenExtractor tokenExtractor;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Login.class)
				|| parameter.hasParameterAnnotation(AnonymousAvailable.class);
	}

	@Override
	public Object resolveArgument(
			MethodParameter parameter,
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory)
			throws Exception {
		if (!parameter.hasMethodAnnotation(AnonymousAvailable.class)) {
			return getUserInfo(webRequest);
		}

		try {
			return getUserInfo(webRequest);
		} catch (AuthorizationException e) {
			return NONE_MEMBER;
		}
	}

	private Long getUserInfo(NativeWebRequest webRequest) {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		String token = tokenExtractor.extract(Objects.requireNonNull(request));
		return tokenResolver.getUserInfo(token);
	}
}
