package com.kakao.golajuma.vote.persistence.repository;

import com.kakao.golajuma.vote.persistence.entity.OptionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OptionRepository extends JpaRepository<OptionEntity, Long> {

	@Query("select o from OptionEntity o where o.voteId = :voteId")
	List<OptionEntity> findAllByVoteId(@Param("voteId") Long voteId);
}
