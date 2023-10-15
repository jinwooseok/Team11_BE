package com.kakao.golajuma.comment.web.controller;

import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.comment.domain.service.CommentService;
import com.kakao.golajuma.comment.web.dto.request.SaveCommentRequest;
import com.kakao.golajuma.comment.web.dto.request.UpdateCommentRequest;
import com.kakao.golajuma.comment.web.dto.response.ReadCommentListResponse;
import com.kakao.golajuma.comment.web.dto.response.SaveCommentResponse;
import com.kakao.golajuma.comment.web.dto.response.UpdateCommentResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/votes/{voteId}/comments")
public class CommentController {

	private final CommentService commentService;

	@GetMapping
	public ApiResponse<ApiResponseBody.SuccessBody<ReadCommentListResponse>> readList(
			@PathVariable Long voteId, @Login Long userId) {
		ReadCommentListResponse responseData = commentService.readList(voteId, userId);
		return ApiResponseGenerator.success(responseData, HttpStatus.OK, MessageCode.GET);
	}

	@PostMapping
	public ApiResponse<ApiResponseBody.SuccessBody<SaveCommentResponse>> create(
			@PathVariable Long voteId,
			@Valid @RequestBody SaveCommentRequest requestDto,
			@Login Long userId) {
		SaveCommentResponse responseData = commentService.create(requestDto, voteId, userId);
		return ApiResponseGenerator.success(responseData, HttpStatus.OK, MessageCode.CREATE);
	}

	@PutMapping("/{commentId}")
	public ApiResponse<ApiResponseBody.SuccessBody<UpdateCommentResponse>> update(
			@PathVariable Long commentId,
			@Valid @RequestBody UpdateCommentRequest requestDto,
			@Login Long userId) {
		UpdateCommentResponse responseData = commentService.update(requestDto, commentId, userId);
		return ApiResponseGenerator.success(responseData, HttpStatus.OK, MessageCode.UPDATE);
	}

	@DeleteMapping("/{commentId}")
	public ApiResponse<ApiResponseBody.SuccessBody<Void>> delete(
			@PathVariable Long commentId, @Login Long userId) {
		commentService.delete(commentId, userId);
		return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.DELETE);
	}
}
