package com.kakao.golajuma.vote.util;

import com.kakao.golajuma.vote.domain.exception.NullException;
import com.kakao.golajuma.vote.domain.exception.OptionNumException;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import java.util.List;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OptionValidator
		implements ConstraintValidator<OptionCheck, List<CreateVoteRequest.OptionDTO>> {

	private static final int MIN_NUM = 2;
	private static final int MAX_NUM = 6;

	@Override
	public boolean isValid(
			List<CreateVoteRequest.OptionDTO> value, ConstraintValidatorContext context) {
		Objects.requireNonNull(value);
		if (checkOptionName(value)) {
			throw new NullException("옵션명은 필수입니다.");
		}
		if (checkOptionNum(value)) {
			// TODO : HV000028: Unexpected exception during isValid call 에러 해결해야함
			throw new OptionNumException("옵션 개수는 2개 이상 6개 이하여야 합니다.");
		}
		return true;
	}

	public boolean checkOptionName(List<CreateVoteRequest.OptionDTO> value) {
		return value.stream().anyMatch(this::isNull);
	}

	public boolean isNull(CreateVoteRequest.OptionDTO value) {
		return value.getName() == null;
	}

	public boolean checkOptionNum(List<CreateVoteRequest.OptionDTO> value) {
		int size = value.size();
		return size < MIN_NUM || size > MAX_NUM;
	}
}
