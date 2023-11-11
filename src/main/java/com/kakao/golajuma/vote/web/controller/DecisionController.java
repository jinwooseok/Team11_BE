package com.kakao.golajuma.vote.web.controller;

import com.kakao.golajuma.auth.web.support.Login;
import com.kakao.golajuma.common.support.respnose.ApiResponse;
import com.kakao.golajuma.common.support.respnose.ApiResponseBody.SuccessBody;
import com.kakao.golajuma.common.support.respnose.ApiResponseGenerator;
import com.kakao.golajuma.common.support.respnose.MessageCode;
import com.kakao.golajuma.vote.domain.service.CreateDecisionService;
import com.kakao.golajuma.vote.domain.service.DeleteDecisionService;
import com.kakao.golajuma.vote.domain.service.UpdateDecisionService;
import com.kakao.golajuma.vote.web.dto.response.DecisionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/votes/participate")
public class DecisionController {
	private final CreateDecisionService createDecisionService;
	private final UpdateDecisionService updateDecisionService;
	private final DeleteDecisionService deleteDecisionService;

	@PostMapping("/options/{optionId}")
	public ApiResponse<SuccessBody<DecisionResponse>> createVote(
			@PathVariable Long optionId, @Login Long userId) {
		DecisionResponse response = createDecisionService.execute(userId, optionId);
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.CREATE);
	}

	@DeleteMapping("/options/{optionId}")
	public ApiResponse<SuccessBody<DecisionResponse>> deleteVote(
			@PathVariable Long optionId, @Login Long userId) {
		DecisionResponse response = deleteDecisionService.execute(userId, optionId);
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.DELETE);
	}

	@PutMapping("/options/{optionId}")
	public ApiResponse<SuccessBody<DecisionResponse>> updateVote(
			@PathVariable Long optionId, @Login Long userId) {
		DecisionResponse response = updateDecisionService.execute(userId, optionId);
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.UPDATE);
	}
}
