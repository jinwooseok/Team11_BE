package com.kakao.golajuma.vote.util;

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
		for (CreateVoteRequest.OptionDTO option : value) {
			if (option.getName() == null) {
				// 기존 메세지 비활성화
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("옵션 이름을 입력해주세요.").addConstraintViolation();
				//                context.
				// 새로운 메세지 추가
				return false;
			}
		}
		return value.size() >= MIN_NUM && value.size() <= MAX_NUM;
	}
}
