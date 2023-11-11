package com.kakao.golajuma.comment.persistence.repository;

import com.kakao.golajuma.comment.persistence.entity.CommentEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
	@Query("select c from CommentEntity c where c.voteId = :voteId and c.deleted = false")
	List<CommentEntity> findByVoteId(@Param("voteId") Long voteId);

	@Query("select c from CommentEntity c where c.userId = :userId and c.deleted = false")
	List<CommentEntity> findByUserId(@Param("userId") Long userId);

	@Query(
			"select c from CommentEntity c where c.userId = :userId and c.id = :commentId and c.deleted = false")
	Optional<CommentEntity> findByCommentIdUserId(
			@Param("commentId") Long id, @Param("userId") Long userId);

	@Query("select count(c) from CommentEntity c where c.voteId = :voteId and c.deleted = false")
	int countByVoteId(@Param("voteId") Long voteId);

	@Query("select c from CommentEntity c where c.voteId = :voteId and c.userId = :userId")
	List<CommentEntity> findAllByVoteIdAndUserId(
			@Param("voteId") Long voteId, @Param("userId") Long userId);
}
