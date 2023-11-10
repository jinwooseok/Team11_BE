package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.domain.exception.NotFoundUserException;
import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.response.UserProfileResponse;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetUserProfileService {

	private final UserRepository userRepository;
	private final VoteRepository voteRepository;

	public UserProfileResponse execute(Long userId) {

		UserEntity userEntity = validateUserEntity(userId);
		int createVoteCount = countCreatedVote(userId);
		int participateVoteCount = countParticipatedVote(userId);

		return userProfileConverter(userEntity, createVoteCount, participateVoteCount);
	}

	protected UserEntity validateUserEntity(Long userId) {
		return userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
	}

	protected int countCreatedVote(Long userId) {
		return voteRepository.findAllByUserId(userId).size();
	}

	protected int countParticipatedVote(Long userId) {
		return voteRepository.findAllParticipateListByUserId(userId).size();
	}

	protected UserProfileResponse userProfileConverter(
			UserEntity userEntity, int createVoteCount, int participateVoteCount) {
		return UserProfileResponse.builder()
				.email(userEntity.getEmail())
				.nickname(userEntity.getNickname())
				.createVoteCount(createVoteCount)
				.participateVoteCount(participateVoteCount)
				.build();
	}
}
