package com.kakao.golajuma.vote.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionValidator.class)
public @interface OptionCheck {
	String message() default "옵션 개수는 2개 이상 6개 이상이어야 합니다.";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
