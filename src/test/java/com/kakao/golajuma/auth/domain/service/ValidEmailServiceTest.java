package com.kakao.golajuma.auth.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import com.kakao.golajuma.auth.domain.exception.DuplicatedEmailException;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.request.SaveUserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidEmailServiceTest {
	@Mock private UserRepository userRepository;

	@InjectMocks private ValidEmailService validEmailService;

	@Test
	@DisplayName("이메일 중복되면 예외가 발생한다.")
	public void duplicated_email() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().email("email@email").build();

		Mockito.when(userRepository.existsByEmail(request.getEmail())).thenReturn(Boolean.TRUE);

		// when & then
		assertThrows(DuplicatedEmailException.class, () -> validEmailService.execute(request));
	}

	@Test
	@DisplayName("이메일 중복이 되지 않으면 예외가 발생하지 않는다.")
	public void not_duplicated_email() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().email("email@email").build();

		Mockito.when(userRepository.existsByEmail(request.getEmail())).thenReturn(Boolean.FALSE);

		// when & then
		assertDoesNotThrow(() -> validEmailService.execute(request));
	}
}
