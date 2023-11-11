package com.kakao.golajuma.auth.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import com.kakao.golajuma.auth.domain.exception.DuplicatedNicknameException;
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
class ValidNicknameServiceTest {
	@Mock private UserRepository userRepository;

	@InjectMocks private ValidNicknameService validNicknameService;

	@Test
	@DisplayName("닉네임 중복되면 예외가 발생한다.")
	void duplicated_nickname() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().nickname("nickname").build();

		Mockito.when(userRepository.existsByNickname(request.getNickname())).thenReturn(Boolean.TRUE);

		// when & then
		assertThrows(DuplicatedNicknameException.class, () -> validNicknameService.execute(request));
	}

	@Test
	@DisplayName("닉네임 중복이 되지 않으면 예외가 발생하지 않는다.")
	void not_duplicated_nickname() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().nickname("nickname").build();

		Mockito.when(userRepository.existsByNickname(request.getNickname())).thenReturn(Boolean.FALSE);

		// when & then
		assertDoesNotThrow(() -> validNicknameService.execute(request));
	}
}
