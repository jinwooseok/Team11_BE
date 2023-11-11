package com.kakao.golajuma.comment.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.golajuma.comment.domain.exception.NotFoundCommentException;
import com.kakao.golajuma.comment.persistence.entity.CommentEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Import(ObjectMapper.class)
@DataJpaTest
@EnableJpaAuditing
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {
	@Autowired private CommentRepository commentRepository;

	@BeforeEach
	void setUp() {
		// given
		CommentEntity commentEntity1 =
				CommentEntity.builder()
						.id(1L)
						.voteId(1L)
						.userId(1L)
						.content("content1")
						.deleted(false)
						.build();
		CommentEntity commentEntity2 =
				CommentEntity.builder()
						.id(2L)
						.voteId(2L)
						.userId(2L)
						.content("content2")
						.deleted(false)
						.build();
		commentRepository.save(commentEntity1);
		commentRepository.save(commentEntity2);
	}

	@Test
	@Transactional
	@DisplayName("댓글을 저장합니다.")
	void comment_save_test() {
		// given
		CommentEntity commentEntity =
				CommentEntity.builder().voteId(1L).userId(1L).content("content1").build();
		// when
		CommentEntity result = commentRepository.save(commentEntity);
		// then
		assertThat(result.getContent()).isEqualTo("content1");
	}

	@Test
	@Transactional
	@DisplayName("댓글을 id에 따라 호출합니다.")
	void comment_findBycommentIdUserId_test() {
		// when
		CommentEntity response =
				commentRepository.findByCommentIdUserId(1L, 1L).orElseThrow(NotFoundCommentException::new);
		// then
		assertThat(response.getClass()).isEqualTo(CommentEntity.class);
		assertThat(response.getUserId()).isEqualTo(1L);
		assertThat(response.getContent()).isEqualTo("content1");
	}

	@Test
	@Transactional
	@DisplayName("댓글을 투표의 id에 따라 호출합니다.")
	void comment_findByVoteId_test() {
		// when
		List<CommentEntity> response = commentRepository.findByVoteId(2L);
		// then
		assertThat(response.get(0).getClass()).isEqualTo(CommentEntity.class);
		assertThat(response.get(0).getUserId()).isEqualTo(2L);
		assertThat(response.get(0).getContent()).isEqualTo("content2");
	}

	@Test
	@Transactional
	@DisplayName("댓글을 유저의 id에 따라 호출합니다.")
	void comment_findByUserId_test() {
		// when
		List<CommentEntity> response = commentRepository.findByUserId(1L);
		// then
		assertThat(response.get(0).getClass()).isEqualTo(CommentEntity.class);
		assertThat(response.get(0).getUserId()).isEqualTo(1L);
		assertThat(response.get(0).getContent()).isEqualTo("content1");
	}
}
