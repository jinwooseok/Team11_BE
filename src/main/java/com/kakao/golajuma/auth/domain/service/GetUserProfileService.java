package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.domain.exception.NotFoundUserException;
import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.response.UserProfileResponse;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
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

		return UserProfileResponse.from(userEntity, createVoteCount, participateVoteCount);
	}

	private UserEntity validateUserEntity(Long userId) {
		return userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
	}

	private int countCreatedVote(Long userId) {
		return voteRepository.findAllByUserId(userId).size();
	}

	private int countParticipatedVote(Long userId) {
		return voteRepository.findAllParticipateListByUserId(userId).size();
	}
}
