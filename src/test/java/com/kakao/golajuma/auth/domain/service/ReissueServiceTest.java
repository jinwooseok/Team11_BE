package com.kakao.golajuma.auth.domain.service;

import static org.mockito.Mockito.*;

import com.kakao.golajuma.auth.domain.exception.NotFoundTokenException;
import com.kakao.golajuma.auth.domain.token.TokenResolver;
import com.kakao.golajuma.auth.persistence.entity.AuthInfoEntity;
import com.kakao.golajuma.auth.persistence.repository.AuthInfoRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReissueServiceTest {
	@Mock private CreateTokenService createTokenService;
	@Mock private AuthInfoRepository authInfoRepository;
	@Mock private TokenResolver tokenResolver;
	@InjectMocks private ReissueService reissueService;

	@Test
	@DisplayName("재발급 요청한 리프레시 토큰이 존재하지 않은 토큰일 때 예외가 발생한다.")
	void exception_when_not_found_token() {
		// given
		when(authInfoRepository.findByUserIdAndToken(any(), any()))
				.thenThrow(NotFoundTokenException.class);

		// when & then
		Assertions.assertThrows(NotFoundTokenException.class, () -> reissueService.execute(any()));
	}

	@Test
	@DisplayName("리프레시 토큰을 통해 토큰을 제발급한다.")
	void reissue() {
		// given
		AuthInfoEntity authInfoEntity =
				AuthInfoEntity.builder().id(1L).userId(1L).token("token").build();

		when(authInfoRepository.findByUserIdAndToken(any(), any()))
				.thenReturn(Optional.of(authInfoEntity));

		// when & then
		reissueService.execute(any());

		// then
		verify(authInfoRepository).delete(authInfoEntity);
		verify(createTokenService).execute(authInfoEntity.getUserId());
	}
}
