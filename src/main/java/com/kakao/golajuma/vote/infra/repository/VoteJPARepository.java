package com.kakao.golajuma.vote.infra.repository;

import com.kakao.golajuma.vote.infra.entity.Category;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteJPARepository extends JpaRepository<VoteEntity, Integer> {

	@Query(
			"select v from VoteEntity v where v.voteActive = :active and v.category = :category ORDER BY v.createdDate desc ")
	List<VoteEntity> findAllByActiveAndCategoryOrderByCreatedDate(
			@Param("active") String active, @Param("category") Category category, Pageable pageable);

	@Query(
			"select v from VoteEntity v where v.voteActive = :active and v.category = :category ORDER BY v.voteTotalCount desc ")
	List<VoteEntity> findAllByActiveAndCategoryOrderByVoteTotalCount(
			@Param("active") String active, @Param("category") Category category, Pageable pageable);

	@Query("select v from VoteEntity v where v.userId = :userId order by v.createdDate desc ")
	List<VoteEntity> findAllByUserId(@Param("userId") long userId);
}
