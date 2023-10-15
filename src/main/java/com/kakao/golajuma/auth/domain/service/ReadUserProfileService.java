package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.infra.entity.UserEntity;
import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.response.UserProfileResponse;
import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.vote.infra.repository.DecisionRepository;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadUserProfileService {
	private final UserRepository userRepository;
	private final VoteRepository voteRepository;
	private final DecisionRepository decisionRepository;

	public UserProfileResponse execute(@Login Long userId) {
		UserEntity userEntity = userRepository.findById(userId).get();

		//        int createVoteCount = voteRepository.countByUserId(userId);
		//        int participateVoteCount = decisionRepository.countByUserId(userId);
		int createVoteCount = 0;
		int participateVoteCount = 0;
		UserProfileResponse response =
				UserProfileResponse.builder()
						.email(userEntity.getEmail())
						.nickName(userEntity.getNickname())
						.image("...")
						.createVoteCount(createVoteCount)
						.participateVoteCount(participateVoteCount)
						.build();
		return response;
	}
}
