package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.comment.persistence.entity.CommentEntity;
import com.kakao.golajuma.comment.persistence.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.CreateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.CreateCommentResponse;
import com.kakao.golajuma.vote.domain.exception.decision.NotFoundDecisionVoteException;
import com.kakao.golajuma.vote.persistence.entity.OptionEntity;
import com.kakao.golajuma.vote.persistence.repository.DecisionRepository;
import com.kakao.golajuma.vote.persistence.repository.OptionRepository;
import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CreateCommentService {

	private final CommentRepository commentRepository;

	private final GetUserNameService getUserNameService;

	private final DecisionRepository decisionRepository;

	private final OptionRepository optionRepository;

	/**
	 * 댓글을 생성한다.
	 *
	 * @param requestDto 생성할 댓글을 포함하고 있음
	 * @param voteId 투표 식별자
	 * @param userId 유저 식별자
	 * @return 생성한 댓글의 정보
	 */
	public CreateCommentResponse execute(CreateCommentRequest requestDto, Long voteId, Long userId) {

		if (!existDecisionByVote(userId, voteId)) throw new NotFoundDecisionVoteException();

		CommentEntity commentEntity = saveComment(requestDto.toEntity(voteId, userId));

		String username = getUserNameService.execute(userId);

		return new CreateCommentResponse(commentEntity, true, username);
	}

	private boolean existDecisionByVote(Long userId, Long voteId) {
		List<OptionEntity> options = optionRepository.findAllByVoteId(voteId);

		return options.stream()
				.map(option -> decisionRepository.existsByUserIdAndOptionId(userId, option.getId()))
				.anyMatch(Predicate.isEqual(true));
	}

	private CommentEntity saveComment(CommentEntity commentEntity) {
		return commentRepository.save(commentEntity);
	}
}
