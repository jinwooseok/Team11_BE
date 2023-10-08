package com.kakao.golajuma.vote.infra.repository;

import com.kakao.golajuma.vote.infra.entity.Category;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteJPARepository extends JpaRepository<VoteEntity, Integer> {

	@Query(
			"select v from VoteEntity v where v.deleted = false and v.id < :idx and v.voteActive = :active and v.category = :category ORDER BY v.createdDate desc ")
	Slice<VoteEntity> findAllByActiveAndCategoryOrderByCreatedDate(
			@Param("idx") long idx,
			@Param("active") String active,
			@Param("category") Category category,
			Pageable pageable);

	@Query(
			"select v from VoteEntity v where v.deleted = false and v.voteTotalCount < :totalCount and v.voteActive = :active and v.category = :category ORDER BY v.voteTotalCount desc ")
	Slice<VoteEntity> findAllByActiveAndCategoryOrderByVoteTotalCount(
			@Param("totalCount") long idx,
			@Param("active") String active,
			@Param("category") Category category,
			Pageable pageable);

	@Query(
			"select v from VoteEntity v where v.deleted = false and v.userId = :userId order by v.createdDate desc ")
	List<VoteEntity> findAllByUserId(@Param("userId") long userId);

	//	// 검색 기능
	//	@Query("select v from VoteEntity v where v.deleted = false and v.voteTitle like %:keyword%
	// order by v.createdDate desc ")
	//	Slice<VoteEntity> searchVotes(@Param("keyword") String keyword, Pageable pageable);

}

