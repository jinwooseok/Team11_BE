package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.domain.exception.RequestParamException;
import com.kakao.golajuma.vote.infra.entity.Category;
import com.kakao.golajuma.vote.infra.entity.VoteEntity;
import com.kakao.golajuma.vote.infra.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.SearchVoteListResponse;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SearchVoteListService {

	private final VoteRepository voteRepository;
	private final GetVoteService getVoteService;
	static int page = 0;
	static int size = 5;

	public SearchVoteListResponse searchVoteList(
			Long userId, int page, String keyword, String sort, String category) {
		this.page = page;

		// 1. vote list 를 가져온다
		Slice<VoteEntity> voteList = findByRepository(keyword, sort, checkCategory(category));

		List<VoteDto> votes = new ArrayList<>();

		voteList.stream()
				.map(voteEntity -> getVoteService.getVote(voteEntity, userId))
				.collect(Collectors.toList());

		// 마지막 페이지인지 검사
		boolean isLast = voteList.isLast();

		return new SearchVoteListResponse(votes, isLast);
	}

	private Slice<VoteEntity> findByRepository(String keyword, String sort, Category category) {
		Pageable pageable = PageRequest.of(page, size);

		if (sort.equals("current")) {
			return voteRepository.searchVotesOrderByCreatedDate(keyword, category, pageable);
		}
		if (sort.equals("popular")) {
			return voteRepository.searchVotesOrderByVoteTotalCount(keyword, category, pageable);
		}

		throw new RequestParamException("잘못된 요청입니다.(sort)");
	}

	private Category checkCategory(String category) {
		return Category.findCategory(category);
	}
}
