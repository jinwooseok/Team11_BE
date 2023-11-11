package com.kakao.golajuma.vote.persistence.repository;

import com.kakao.golajuma.vote.persistence.entity.Category;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<VoteEntity, Integer> {

	@Query(
			"select v from VoteEntity v"
					+ " where v.deleted = false"
					+ " and v.voteEndDate > :now"
					+ " ORDER BY v.createdDate desc ")
	Slice<VoteEntity> findAllContinueVotesOrderByCreatedDate(
			@Param("now") LocalDateTime now, Pageable pageable);

	@Query(
			"select v from VoteEntity v"
					+ " where v.deleted = false"
					+ " and v.voteEndDate > :now"
					+ " and v.category = :category"
					+ " ORDER BY v.createdDate desc ")
	Slice<VoteEntity> findAllContinueVotesByCategoryOrderByCreatedDate(
			@Param("now") LocalDateTime now, @Param("category") Category category, Pageable pageable);

	@Query(
			"select v from VoteEntity v"
					+ " where v.deleted = false"
					+ " and v.voteEndDate > :now"
					+ " ORDER BY v.voteTotalCount desc ")
	Slice<VoteEntity> findAllContinueVotesOrderByVoteTotalCount(
			@Param("now") LocalDateTime now, Pageable pageable);

	@Query(
			"select v from VoteEntity v"
					+ " where v.deleted = false"
					+ " and v.voteEndDate > :now"
					+ " and v.category = :category"
					+ " ORDER BY v.voteTotalCount desc ")
	Slice<VoteEntity> findAllContinueVotesByCategoryOrderByVoteTotalCount(
			@Param("now") LocalDateTime now, @Param("category") Category category, Pageable pageable);

	@Query(
			"select v from VoteEntity v"
					+ " where v.deleted = false"
					+ " and v.voteEndDate < :now"
					+ " ORDER BY v.createdDate desc ")
	Slice<VoteEntity> findAllFinishVotesOrderByCreatedDate(
			@Param("now") LocalDateTime now, Pageable pageable);

	@Query(
			"select v from VoteEntity v"
					+ " where v.deleted = false"
					+ " and v.voteEndDate < :now"
					+ " and v.category = :category"
					+ " ORDER BY v.createdDate desc ")
	Slice<VoteEntity> findAllFinishVotesByCategoryOrderByCreatedDate(
			@Param("now") LocalDateTime now, @Param("category") Category category, Pageable pageable);

	@Query(
			"select v from VoteEntity v"
					+ " where v.deleted = false"
					+ " and v.voteEndDate < :now"
					+ " ORDER BY v.voteTotalCount desc ")
	Slice<VoteEntity> findAllFinishVotesOrderByVoteTotalCount(
			@Param("now") LocalDateTime now, Pageable pageable);

	@Query(
			"select v from VoteEntity v"
					+ " where v.deleted = false"
					+ " and v.voteEndDate < :now"
					+ " and v.category = :category"
					+ " ORDER BY v.voteTotalCount desc ")
	Slice<VoteEntity> findAllFinishVotesByCategoryOrderByVoteTotalCount(
			@Param("now") LocalDateTime now, @Param("category") Category category, Pageable pageable);

	@Query(
			"select v from VoteEntity v where v.deleted = false and v.userId = :userId order by v.createdDate desc ")
	List<VoteEntity> findAllByUserId(@Param("userId") Long userId);

	Optional<VoteEntity> findById(Long id);

	// 검색 기능
	@Query(
			"select v from VoteEntity v"
					+ " where v.deleted = false"
					+ " and v.voteTitle like %:keyword%"
					+ " order by v.createdDate desc ")
	Slice<VoteEntity> searchVotesOrderByCreatedDate(
			@Param("keyword") String keyword, Pageable pageable);

	@Query(
			"select v from VoteEntity v"
					+ " where v.deleted = false"
					+ " and v.voteTitle like %:keyword%"
					+ " and v.category = :category"
					+ " order by v.createdDate desc ")
	Slice<VoteEntity> searchVotesByCategoryOrderByCreatedDate(
			@Param("keyword") String keyword, @Param("category") Category category, Pageable pageable);

	@Query(
			"select v from VoteEntity v"
					+ " where v.deleted = false"
					+ " and v.voteTitle like %:keyword%"
					+ " order by v.voteTotalCount desc ")
	Slice<VoteEntity> searchVotesOrderByVoteTotalCount(
			@Param("keyword") String keyword, Pageable pageable);

	@Query(
			"select v from VoteEntity v"
					+ " where v.deleted = false"
					+ " and v.voteTitle like %:keyword%"
					+ " and v.category = :category"
					+ " order by v.voteTotalCount desc ")
	Slice<VoteEntity> searchVotesByCategoryOrderByVoteTotalCount(
			@Param("keyword") String keyword, @Param("category") Category category, Pageable pageable);

	@Query(
			"select v from VoteEntity v"
					+ " join OptionEntity o on o.voteId = v.id"
					+ " where o.id = :optionId")
	Optional<VoteEntity> findVoteByOption(Long optionId);

	// 유저가 참여한 투표 찾기
	@Query(
			"select v from VoteEntity v"
					+ " join DecisionEntity d on d.userId = :userId"
					+ " join OptionEntity o on o.id = d.optionId and o.voteId = v.id"
					+ " where v.deleted = false"
					+ " order by v.createdDate desc")
	List<VoteEntity> findAllParticipateListByUserId(Long userId);

	@Query(
			"SELECT v "
					+ "FROM VoteEntity v "
					+ "JOIN OptionEntity o ON o.voteId = v.id "
					+ "JOIN DecisionEntity d ON o.id = d.optionId "
					+ "WHERE d.updatedDate >= :startTime AND d.updatedDate < :endTime AND v.deleted = false "
					+ "GROUP BY v.id "
					+ "ORDER BY count(d.id) DESC")
	Slice<VoteEntity> findByTimeLimitAndDecisionCount(
			@Param("startTime") LocalDateTime startTime,
			@Param("endTime") LocalDateTime endTime,
			Pageable pageable);
}
