package com.kakao.golajuma.comment.web.dto.response;

import com.kakao.golajuma.comment.persistence.entity.CommentEntity;
import com.kakao.golajuma.common.marker.AbstractResponseDto;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentDto implements AbstractResponseDto {
	private final Long id;
	private final Boolean isOwner;
	private final String username;
	private final String content;
	private final LocalDateTime createTime;
	/**
	 * 해당 클래스의 객체 생성자
	 *
	 * @implSpec 이 클래스의 하위 클래스들은 각각의 요소를 이 클래스와 같은 요소들로 생성한다.
	 */
	public CommentDto(CommentEntity entity, Boolean isOwner, String username) {
		this.id = entity.getId();
		this.isOwner = isOwner;
		this.username = username;
		this.content = entity.getContent();
		this.createTime = entity.getUpdatedDate();
	}
}
