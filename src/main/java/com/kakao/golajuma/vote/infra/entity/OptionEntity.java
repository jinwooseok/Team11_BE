package com.kakao.golajuma.vote.infra.entity;

import com.kakao.golajuma.common.BaseEntity;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = OptionEntity.ENTITY_PREFIX + "_tb")
public class OptionEntity extends BaseEntity {

	public static final String ENTITY_PREFIX = "option";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ENTITY_PREFIX + "_id")
	private long id;

	@Column(name = ENTITY_PREFIX + "_vote_id", nullable = false)
	private long voteId;

	@Column(name = ENTITY_PREFIX + "_name", nullable = false)
	private String optionName;

	@Column(name = ENTITY_PREFIX + "_image")
	private String optionImage;

	@Column(name = ENTITY_PREFIX + "_count")
	private long optionCount;

	@Builder
	public OptionEntity(long id, long voteId, String optionName, String optionImage) {
		this.id = id;
		this.voteId = voteId;
		this.optionName = optionName;
		this.optionImage = optionImage;
		this.optionCount = 0;
	}

	public static OptionEntity createEntity(CreateVoteRequest.OptionDTO request, long voteId) {
		return OptionEntity.builder()
				.voteId(voteId)
				.optionName(request.getName())
				.optionImage(request.getImage())
				.build();
	}
}
