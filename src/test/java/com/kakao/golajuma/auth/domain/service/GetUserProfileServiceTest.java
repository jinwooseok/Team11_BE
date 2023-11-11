package com.kakao.golajuma.auth.domain.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.kakao.golajuma.auth.domain.exception.NotFoundUserException;
import com.kakao.golajuma.auth.persistence.entity.UserEntity;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import com.kakao.golajuma.auth.web.dto.response.UserProfileResponse;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserProfileServiceTest {
	@InjectMocks private GetUserProfileService getUserProfileService;
	@Mock private UserRepository userRepository;
	@Mock private VoteRepository voteRepository;

	@Nested
	@DisplayName("mypage 요청 성공 시 유저 데이터를 반환한다.")
	class success_get_user_profile {
		@Test
		@DisplayName("유저의 프로필 데이터를 성공적으로 반환한다.")
		void get_user_profile_success_test() {
			// given
			UserEntity userEntity =
					UserEntity.builder().id(1L).email("test@gmail.com").nickname("tester").build();
			List<VoteEntity> createdVoteEntityList = new ArrayList<>();
			List<VoteEntity> participatedVoteEntityList = new ArrayList<>();

			when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
			when(voteRepository.findAllByUserId(anyLong())).thenReturn(createdVoteEntityList);
			when(voteRepository.findAllParticipateListByUserId(anyLong()))
					.thenReturn(participatedVoteEntityList);

			// when
			UserProfileResponse result = getUserProfileService.execute(anyLong());

			// then
			assertThat(result.getNickname()).isEqualTo("tester");
			assertThat(result.getEmail()).isEqualTo("test@gmail.com");
			assertThat(result.getCreateVoteCount()).isEqualTo(createdVoteEntityList.size());
			assertThat(result.getParticipateVoteCount()).isEqualTo(participatedVoteEntityList.size());
		}
	}

	@Nested
	@DisplayName("mypage 요청이 실패 시 예외를 반환한다.")
	class fail_read_user_profile {
		@Test
		@DisplayName("존재하지 않는 유저의 데이터를 요청하는 경우")
		void null_read_user_entity() {
			// given
			when(userRepository.findById(anyLong())).thenThrow(NotFoundUserException.class);
			// when & then
			assertThrows(NotFoundUserException.class, () -> getUserProfileService.execute(anyLong()));
		}
	}
}
