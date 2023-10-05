package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.GetVoteListService;
import com.kakao.golajuma.vote.web.dto.response.GetVoteListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetVoteListController {

	private final GetVoteListService getVoteListService;

	// 투표 조회 - 메인페이지, 완료된 페이지
	@GetMapping("/votes")
	public ApiResponse<ApiResponseBody.SuccessBody<GetVoteListResponse.MainAndFinishPage>>
			getVoteList(
					@RequestParam(defaultValue = "99999999999999999") long idx,
					@RequestParam(defaultValue = "99999999999999999") long totalCount,
					@RequestParam(defaultValue = "current") String sort,
					@RequestParam(defaultValue = "continue") String active,
					@RequestParam(defaultValue = "total") String category) {
		GetVoteListResponse.MainAndFinishPage responseDto =
				getVoteListService.getVoteList(idx, totalCount, sort, active, category);

		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.CREATE);
	}

	// 투표 리스트 조회 - 마이페이지 참여한 투표
	@GetMapping("/users/votes/participate")
	public ApiResponse<ApiResponseBody.SuccessBody<GetVoteListResponse.MyPage>>
			getVoteListInMyPageByParticipate() {
		GetVoteListResponse.MyPage responseDto = getVoteListService.getVoteListInMyPageByParticipate();

		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.CREATE);
	}

	// 투표 리스트 조회 - 마이페이지 올린 투표
	@GetMapping("/users/votes/ask")
	public ApiResponse<ApiResponseBody.SuccessBody<GetVoteListResponse.MyPage>>
			getVoteListInMyPageByAsk() {
		GetVoteListResponse.MyPage responseDto = getVoteListService.getVoteListInMyPageByAsk();

		return ApiResponseGenerator.success(responseDto, HttpStatus.OK, MessageCode.CREATE);
	}
}
