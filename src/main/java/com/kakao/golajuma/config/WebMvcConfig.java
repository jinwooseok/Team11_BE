package com.kakao.golajuma.config;

import com.kakao.golajuma.auth.domain.token.TokenResolver;
import com.kakao.golajuma.auth.web.interceptor.AuthInterceptor;
import com.kakao.golajuma.auth.web.support.AuthenticationPrincipalArgumentResolver;
import com.kakao.golajuma.auth.web.support.TokenExtractor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	private static final long MAX_AGE_SECS = 3600;
	private final AuthInterceptor authInterceptor;
	private final TokenResolver tokenResolver;

	@Qualifier("auth")
	private final TokenExtractor tokenExtractor;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
				.addMapping("/**")
				.allowedOriginPatterns("*")
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
				.allowedHeaders("*")
				.exposedHeaders("Authorization")
				.allowCredentials(true)
				.maxAge(MAX_AGE_SECS);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
				.addInterceptor(authInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/api/auth/**");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(authenticationPrincipalArgumentResolver());
	}

	@Bean
	public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver() {
		return new AuthenticationPrincipalArgumentResolver(tokenResolver, tokenExtractor);
	}
}
