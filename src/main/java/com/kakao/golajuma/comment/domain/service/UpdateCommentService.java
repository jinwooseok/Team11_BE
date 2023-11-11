package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.comment.domain.exception.NotFoundCommentException;
import com.kakao.golajuma.comment.persistence.entity.CommentEntity;
import com.kakao.golajuma.comment.persistence.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.UpdateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.UpdateCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UpdateCommentService {

	private final CommentRepository commentRepository;

	private final GetUserNameService getUserNameService;
	/**
	 * 댓글을 삭제한다.
	 *
	 * @param requestDto 바꾸고 싶은 내용을 담고있는 객체
	 * @param commentId 댓글 식별자
	 * @param userId 유저 식별자
	 * @return 업데이트한 댓글의 정보
	 * @throws NullPointerException 삭제하고 싶은 댓글이 이미 존재하지 않을 때
	 */
	@Transactional
	public UpdateCommentResponse execute(
			UpdateCommentRequest requestDto, Long commentId, Long userId) {

		CommentEntity commentEntity =
				commentRepository
						.findByCommentIdUserId(commentId, userId)
						.orElseThrow(NotFoundCommentException::new);

		String newContent = requestDto.getContent();

		String username = getUserNameService.execute(userId);

		commentEntity.updateContent(newContent);

		return new UpdateCommentResponse(commentEntity, true, username);
	}
}
