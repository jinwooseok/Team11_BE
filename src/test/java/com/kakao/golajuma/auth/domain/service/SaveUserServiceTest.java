package com.kakao.golajuma.auth.domain.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.auth.persistence.converter.UserEntityConverter;
import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.request.SaveUserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaveUserServiceTest {
	@Mock private ValidEmailService validEmailService;

	@Mock private ValidNicknameService validNicknameService;

	@Mock private UserEntityConverter entityConverter;
	@Mock private UserRepository userRepository;

	@InjectMocks private SaveUserService saveUserService;

	@Test
	@DisplayName("회원가입 시 이메일 중복체크를 한다.")
	void email_duplicated_check_when_signup() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().build();

		// when
		saveUserService.execute(request);

		// then
		verify(validEmailService).execute(request);
	}

	@Test
	@DisplayName("회원가입 시 닉네임 중복체크를 한다.")
	void nickname_duplicated_check_when_signup() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().build();

		// when
		saveUserService.execute(request);

		// then
		verify(validNicknameService).execute(request);
	}

	@Test
	@DisplayName("회원가입 시 repository에게 save 메시지를 전달한다.")
	void save_message_when_signup() {
		// given
		SaveUserRequest request = SaveUserRequest.builder().build();
		UserEntity entity = UserEntity.builder().build();

		when(entityConverter.toEntity(request)).thenReturn(entity);

		// when
		saveUserService.execute(request);

		// then
		verify(userRepository).save(entity);
	}
}
