package com.kakao.golajuma.auth.persistence.entity;

import com.kakao.golajuma.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = AuthInfoEntity.ENTITY_PREFIX)
public class AuthInfoEntity extends BaseEntity {

	public static final String ENTITY_PREFIX = "auth_info";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ENTITY_PREFIX + "_id", nullable = false)
	private Long id;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(name = ENTITY_PREFIX + "_type", nullable = false)
	private LoginType type = LoginType.SERVICE;

	@Column(name = ENTITY_PREFIX + "_token", nullable = false)
	private String token;

	@Column(name = "user_id", nullable = false)
	private Long userId;
}
