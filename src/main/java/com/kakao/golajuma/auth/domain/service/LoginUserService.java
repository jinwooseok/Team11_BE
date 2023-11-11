package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.domain.exception.NotFoundEmailException;
import com.kakao.golajuma.auth.domain.exception.NotFoundPasswordException;
import com.kakao.golajuma.auth.domain.helper.Encoder;
import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.request.LoginUserRequest;
import com.kakao.golajuma.auth.web.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginUserService {

	private final UserRepository userRepository;
	private final Encoder encoder;
	private final CreateTokenService createTokenService;

	@Transactional
	public TokenResponse execute(final LoginUserRequest request) {
		UserEntity userEntity =
				userRepository.findByEmail(request.getEmail()).orElseThrow(NotFoundEmailException::new);

		validPassword(request.getPassword(), userEntity);

		return createTokenService.execute(userEntity.getId());
	}

	private void validPassword(final String requestPassword, final UserEntity userEntity) {
		if (!matchPassword(requestPassword, userEntity.getPassword())) {
			throw new NotFoundPasswordException();
		}
	}

	private boolean matchPassword(final String requestPassword, final String password) {
		return encoder.matches(requestPassword, password);
	}
}
