package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.comment.persistence.entity.CommentEntity;
import com.kakao.golajuma.comment.persistence.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DeleteAllCommentByVoteService {
	private final CommentRepository commentRepository;

	public void execute(Long voteId, Long userId) {
		List<CommentEntity> comments = commentRepository.findAllByVoteIdAndUserId(voteId, userId);
		commentRepository.deleteAll(comments);
	}
}
