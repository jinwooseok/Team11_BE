package com.kakao.golajuma.comment.domain.service;

import com.kakao.golajuma.auth.infra.repository.UserRepository;
import com.kakao.golajuma.comment.domain.exception.AuthorizationException;
import com.kakao.golajuma.comment.domain.exception.NoOwnershipException;
import com.kakao.golajuma.comment.domain.exception.NullPointerException;
import com.kakao.golajuma.comment.infra.entity.CommentEntity;
import com.kakao.golajuma.comment.infra.repository.CommentRepository;
import com.kakao.golajuma.comment.web.dto.request.SaveCommentRequest;
import com.kakao.golajuma.comment.web.dto.request.UpdateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.ReadCommentDto;
import com.kakao.golajuma.comment.web.dto.response.ReadCommentListResponse;
import com.kakao.golajuma.comment.web.dto.response.SaveCommentResponse;
import com.kakao.golajuma.comment.web.dto.response.UpdateCommentResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;

	private final UserRepository userRepository;

	@Transactional
	public SaveCommentResponse create(SaveCommentRequest requestDto, Long voteId, Long userId) {
		if (userId == null) throw new AuthorizationException("로그인 후 댓글을 작성할 수 있습니다.");

		// 저장
		CommentEntity commentEntity = requestDto.toEntity(voteId, userId);
		commentRepository.save(commentEntity);
		// return
		String username = userRepository.findById(userId).get().getNickname();
		SaveCommentResponse response = new SaveCommentResponse(commentEntity, true, username);
		return response;
	}

	// 페이지 구현하기 안해둠
	public ReadCommentListResponse readList(Long voteId, Long userId) {

		// 가져오기
		List<CommentEntity> commentEntityList = commentRepository.findByVoteId(voteId);

		int commentCount = commentEntityList.size();

		List<ReadCommentDto> readCommentDtoList = new ArrayList<>();

		for (CommentEntity commentEntity : commentEntityList) {
			// for문 안에서만 사용하는 변수 선언
			boolean isOwner;

			Long id = commentEntity.getUserId();
			System.out.println(id);
			String username = userRepository.findById(userId).get().getNickname();

			// 3. 주인 판별 로직
			isOwner = userId.equals(id);
			ReadCommentDto readCommentDto = new ReadCommentDto(commentEntity, isOwner, username);
			readCommentDtoList.add(readCommentDto);
		}

		ReadCommentListResponse response =
				new ReadCommentListResponse(readCommentDtoList, commentCount);

		return response;
	}

	@Transactional
	public UpdateCommentResponse update(
			UpdateCommentRequest requestDto, Long commentId, Long userId) {
		if (userId == null) throw new AuthorizationException("로그인 후 댓글을 업데이트할 수 있습니다.");

		// 1. 존재하는 댓글인지 확인
		CommentEntity commentEntity =
				commentRepository
						.findById(commentId)
						.orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST));

		// 2. 본인의 comment인지 확인
		if (!commentEntity.isUser(userId)) {
			throw new NoOwnershipException("접근할 수 없습니다.", HttpStatus.FORBIDDEN);
		}

		// repository update >> entity update
		String newContent = requestDto.getContent();
		// setter는 명확하지 않기 때문에 댓글을 업데이트를 한다는걸 명시한 새로운 메서드 정의
		commentEntity.updateContent(newContent);
		String username = userRepository.findById(userId).get().getNickname();

		UpdateCommentResponse response = new UpdateCommentResponse(commentEntity, true, username);
		return response;
	}

	@Transactional
	public void delete(Long commentId, Long userId) {
		if (userId == null) throw new AuthorizationException("로그인 후 댓글을 삭제할 수 있습니다.");
		// 1. 존재하는 댓글인지 확인
		CommentEntity commentEntity =
				commentRepository
						.findById(commentId)
						.orElseThrow(() -> new NullPointerException("댓글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST));

		// 2. 본인의 comment인지 확인
		if (!commentEntity.isUser(userId)) {
			throw new NoOwnershipException("접근할 수 없습니다.", HttpStatus.FORBIDDEN);
		}
		// 삭제로직
		commentEntity.delete();
	}
}
