package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.comment.persistence.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.response.GetCommentCountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class GetCommentCountService {

	private final CommentRepository commentRepository;

	public GetCommentCountResponse execute(Long voteId) {
		return new GetCommentCountResponse(commentRepository.countByVoteId(voteId));
	}
}
