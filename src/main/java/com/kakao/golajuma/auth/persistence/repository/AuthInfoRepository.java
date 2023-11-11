package com.kakao.golajuma.auth.persistence.repository;

import com.kakao.golajuma.auth.persistence.entity.AuthInfoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthInfoRepository extends JpaRepository<AuthInfoEntity, Long> {
	Optional<AuthInfoEntity> findByUserIdAndToken(Long userId, String token);
}
