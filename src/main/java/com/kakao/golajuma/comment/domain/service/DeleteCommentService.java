package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.comment.domain.exception.NotFoundCommentException;
import com.kakao.golajuma.comment.persistence.entity.CommentEntity;
import com.kakao.golajuma.comment.persistence.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class DeleteCommentService {

	private final CommentRepository commentRepository;
	/**
	 * 댓글을 삭제한다.
	 *
	 * @param commentId 댓글 식별자
	 * @param userId 유저 식별자
	 */
	public void execute(Long commentId, Long userId) {

		CommentEntity commentEntity =
				commentRepository
						.findByCommentIdUserId(commentId, userId)
						.orElseThrow(NotFoundCommentException::new);

		commentEntity.delete();
	}
}
