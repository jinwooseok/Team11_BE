package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.auth.domain.exception.NotFoundUserException;
import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetUserNameService {

	private final UserRepository userRepository;

	public String execute(Long userId) {
		UserEntity userEntity = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
		return userEntity.getNickname();
	}
}
