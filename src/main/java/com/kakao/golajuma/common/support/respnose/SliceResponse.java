package com.kakao.golajuma.common.support.respnose;

import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@Getter
public class SliceResponse<T> {

	/** 현 페이지 데이터 개수 */
	private final int pageSize;

	/** 다음 슬라이스 존재 여부 */
	private final boolean hasNext;
	/** 이전 슬라이스 존재 여부 */
	private final boolean hasPrevious;

	private final List<T> data;

	public SliceResponse(final Slice<T> source) {
		final Pageable pageable = source.getPageable();
		this.pageSize = pageable.getPageSize();
		this.hasNext = source.hasNext();
		this.hasPrevious = source.hasPrevious();
		this.data = source.getContent();
	}
}
