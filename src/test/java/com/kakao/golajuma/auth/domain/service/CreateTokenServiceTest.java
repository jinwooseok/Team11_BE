package com.kakao.golajuma.auth.domain.service;

import static org.mockito.Mockito.*;

import com.kakao.golajuma.auth.domain.token.TokenProvider;
import com.kakao.golajuma.auth.domain.token.TokenResolver;
import com.kakao.golajuma.auth.persistence.converter.AuthInfoEntityConverter;
import com.kakao.golajuma.auth.persistence.entity.AuthInfoEntity;
import com.kakao.golajuma.auth.persistence.repository.AuthInfoRepository;
import com.kakao.golajuma.auth.web.dto.converter.TokenConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateTokenServiceTest {

	@Mock private TokenProvider tokenProvider;
	@Mock private TokenConverter tokenConverter;
	@Mock private TokenResolver tokenResolver;
	@Mock private AuthInfoEntityConverter authInfoEntityConverter;
	@Mock private AuthInfoRepository authInfoRepository;
	@InjectMocks private CreateTokenService createTokenService;

	@Test
	@DisplayName("토큰을 생성하고 저장한다.")
	void create_token() {
		// given
		Long userId = 1L;
		String accessToken = "mocked_access_token";
		String refreshToken = "mocked_refresh_token";

		AuthInfoEntity entity =
				AuthInfoEntity.builder().id(1L).userId(userId).token(refreshToken).build();

		when(tokenProvider.createAccessToken(userId)).thenReturn(accessToken);
		when(tokenProvider.createRefreshToken(userId)).thenReturn(refreshToken);
		when(tokenResolver.getExpiredDate(accessToken)).thenReturn(123446L);
		when(tokenResolver.getExpiredDate(refreshToken)).thenReturn(123446L);
		when(authInfoEntityConverter.from(userId, refreshToken)).thenReturn(entity);

		// when
		createTokenService.execute(userId);

		// then
		verify(authInfoRepository).save(entity);
	}
}
