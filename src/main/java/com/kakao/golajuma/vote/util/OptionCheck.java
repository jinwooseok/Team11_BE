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
	String message() default "정상 요청이 아닙니다. [옵션]";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
