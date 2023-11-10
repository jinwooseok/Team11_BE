package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.comment.domain.exception.NullPointerException;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.CreateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.CreateCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CreateCommentService {

	private final CommentRepository commentRepository;

	private final GetUserNameService getUserNameService;

	/**
	 * 댓글을 생성한다.
	 *
	 * @param requestDto 생성할 댓글을 포함하고 있음
	 * @param voteId 투표 식별자
	 * @param userId 유저 식별자
	 * @return 생성한 댓글의 정보
	 * @throws NullPointerException 존재하지 않은 유저가 생성을 요청했을 때
	 */
	public CreateCommentResponse execute(CreateCommentRequest requestDto, Long voteId, Long userId) {

		CommentEntity commentEntity = saveComment(requestDto.toEntity(voteId, userId));
		// return
		String username = getUserNameService.execute(userId);

		return new CreateCommentResponse(commentEntity, true, username);
	}

	private CommentEntity saveComment(CommentEntity commentEntity) {
		return commentRepository.save(commentEntity);
	}
}
