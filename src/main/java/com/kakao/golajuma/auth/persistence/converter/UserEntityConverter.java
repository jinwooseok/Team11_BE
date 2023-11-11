package com.kakao.golajuma.auth.persistence.converter;

import com.kakao.golajuma.auth.domain.helper.Encoder;
import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.auth.web.dto.request.SaveUserRequest;
import com.kakao.golajuma.common.support.converter.AbstractEntityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityConverter implements AbstractEntityConverter<UserEntity, SaveUserRequest> {

	private final Encoder encoder;

	@Override
	public UserEntity toEntity(SaveUserRequest source) {
		return UserEntity.builder()
				.nickname(source.getNickname())
				.email(source.getEmail())
				.password(encoder.encode(source.getPassword()))
				.build();
	}
}
