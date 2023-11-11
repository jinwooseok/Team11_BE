package com.kakao.golajuma.auth.domain.service;

import static org.mockito.Mockito.verify;

import com.kakao.golajuma.auth.persistence.converter.AuthInfoEntityConverter;
import com.kakao.golajuma.auth.persistence.entity.AuthInfoEntity;
import com.kakao.golajuma.auth.persistence.repository.AuthInfoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveTokenServiceTest {
	@Mock private AuthInfoRepository authInfoRepository;
	@Mock private AuthInfoEntityConverter authInfoEntityConverter;
	@InjectMocks private SaveTokenService saveTokenService;

	@Test
	@DisplayName("토큰을 저장한다.")
	void save_token() {
		// given
		Long userId = 1L;
		String token = "token";

		AuthInfoEntity authInfoEntity =
				AuthInfoEntity.builder().id(1L).userId(userId).token(token).build();

		Mockito.when(authInfoEntityConverter.from(userId, token)).thenReturn(authInfoEntity);

		// when
		saveTokenService.execute(userId, token);

		// then
		verify(authInfoRepository).save(authInfoEntity);
	}
}
