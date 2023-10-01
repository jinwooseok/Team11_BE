package com.kakao.golajuma.vote.infra.repository;

import com.kakao.golajuma.vote.infra.entity.OptionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OptionJPARepository extends JpaRepository<OptionEntity, Integer> {

	@Query("select o from OptionEntity where o.voteId = :voteId")
	List<OptionEntity> findAllByVoteId(@Param("voteID") long voteId);
}
