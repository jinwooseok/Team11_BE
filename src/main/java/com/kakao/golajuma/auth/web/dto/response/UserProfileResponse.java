package com.kakao.golajuma.auth.web.dto.response;

import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserProfileResponse {

	private String nickname;

	private String email;

	private int createVoteCount;

	private int participateVoteCount;

	public static UserProfileResponse from(
			UserEntity userEntity, int createVoteCount, int participateVoteCount) {
		return UserProfileResponse.builder()
				.email(userEntity.getEmail())
				.nickname(userEntity.getNickname())
				.createVoteCount(createVoteCount)
				.participateVoteCount(participateVoteCount)
				.build();
	}
}
