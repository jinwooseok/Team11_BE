package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.domain.exception.NotFoundUserException;
import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.request.UpdateUserNickNameRequest;
import com.kakao.golajuma.auth.web.dto.response.UpdateNickNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateUserNickNameService {

	private final UserRepository userRepository;

	private final ValidNicknameService validNicknameService;

	public UpdateNickNameResponse execute(UpdateUserNickNameRequest requestDto, Long userId) {
		validNicknameService.execute(requestDto);
		UserEntity userEntity = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
		String newNickName = requestDto.getNickname();
		userEntity.updateNickName(newNickName);
		return new UpdateNickNameResponse(newNickName);
	}
}
