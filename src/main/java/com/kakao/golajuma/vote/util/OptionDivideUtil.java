package com.kakao.golajuma.vote.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OptionDivideUtil {

	private static final Long NOT_DIVIDE = 0L;
	private static final double ZERO = 0;

	public static double getRatio(Long optionCount, Long totalCount) {
		if (totalCount.equals(NOT_DIVIDE)) {
			return ZERO;
		}

		double option = optionCount.doubleValue();
		double total = totalCount.doubleValue();

		return Math.round(((option / total * 100) * 10) / 10);
	}
}
