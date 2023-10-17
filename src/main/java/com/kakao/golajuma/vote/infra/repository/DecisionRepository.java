package com.kakao.golajuma.vote.infra.repository;

import com.kakao.golajuma.vote.infra.entity.DecisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecisionRepository extends JpaRepository<DecisionEntity, Long> {}
