package com.kakao.golajuma.auth.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.kakao.golajuma.auth.domain.exception.NotFoundEmailException;
import com.kakao.golajuma.auth.domain.exception.NotFoundPasswordException;
import com.kakao.golajuma.auth.domain.helper.Encoder;
import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.request.LoginUserRequest;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginUserServiceTest {
	@Mock private UserRepository userRepository;
	@Mock private Encoder encoder;
	@Mock private CreateTokenService createTokenService;
	@InjectMocks private LoginUserService loginUserService;

	@Test
	@DisplayName("존재하지 않은 이메일이면 예외가 발생한다.")
	void exception_when_not_found_email() {
		// given
		LoginUserRequest request =
				LoginUserRequest.builder().email("email@example.com").password("password").build();

		when(userRepository.findByEmail(any())).thenThrow(NotFoundEmailException.class);

		// when & then
		assertThrows(NotFoundEmailException.class, () -> loginUserService.execute(request));
	}

	@Test
	@DisplayName("존재하지 않은 비밀번호면 예외가 발생한다.")
	void exception_when_not_found_password() {
		// given
		LoginUserRequest request =
				LoginUserRequest.builder().email("email@example.com").password("password").build();

		UserEntity entity =
				UserEntity.builder().id(1L).email("email@example.com").password("password").build();

		when(userRepository.findByEmail(any())).thenReturn(java.util.Optional.of(entity));
		when(encoder.matches(request.getPassword(), entity.getPassword())).thenReturn(false);

		// when & then
		assertThrows(NotFoundPasswordException.class, () -> loginUserService.execute(request));
	}

	@Test
	@DisplayName("존재하는 사용자일 경우 토큰을 생성한다.")
	void success_login() {
		// given
		LoginUserRequest request =
				LoginUserRequest.builder().email("email@example.com").password("password").build();

		UserEntity entity =
				UserEntity.builder().id(1L).email("email@example.com").password("password").build();

		when(userRepository.findByEmail(any())).thenReturn(Optional.of(entity));
		when(encoder.matches(request.getPassword(), entity.getPassword())).thenReturn(true);

		// when
		loginUserService.execute(request);

		// then
		verify(createTokenService, times(1)).execute(any());
	}
}
