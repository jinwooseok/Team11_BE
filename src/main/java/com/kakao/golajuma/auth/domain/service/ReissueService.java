package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.domain.exception.NotFoundTokenException;
import com.kakao.golajuma.auth.domain.token.TokenResolver;
import com.kakao.golajuma.auth.persistence.entity.AuthInfoEntity;
import com.kakao.golajuma.auth.persistence.repository.AuthInfoRepository;
import com.kakao.golajuma.auth.web.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ReissueService {
	private final CreateTokenService createTokenService;
	private final AuthInfoRepository authInfoRepository;
	private final TokenResolver tokenResolver;

	@Transactional
	public TokenResponse execute(final String token) {
		AuthInfoEntity authInfoEntity = getExistAuthInfo(token);
		authInfoRepository.delete(authInfoEntity);

		return createTokenService.execute(authInfoEntity.getUserId());
	}

	private AuthInfoEntity getExistAuthInfo(String token) {
		Long userId = tokenResolver.getUserInfo(token);

		return authInfoRepository
				.findByUserIdAndToken(userId, token)
				.orElseThrow(NotFoundTokenException::new);
	}
}
