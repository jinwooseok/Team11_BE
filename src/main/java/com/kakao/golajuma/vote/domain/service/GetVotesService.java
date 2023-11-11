package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.persistence.entity.Active;
import com.kakao.golajuma.vote.persistence.entity.Category;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import com.kakao.golajuma.vote.web.dto.response.GetVotesResponse;
import com.kakao.golajuma.vote.web.dto.response.VoteDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GetVotesService {

	private final VoteRepository voteRepository;
	private final GetVoteService getVoteService;

	static int page = 0;
	static int size = 5;

	public GetVotesResponse.MainAndFinishPage execute(
			Long userId, int page, Sort sort, Active active, Category category) {
		this.page = page;

		Slice<VoteEntity> voteEntities = getVoteListByRequest(active, category, sort);

		List<VoteDto> votes = new ArrayList<>();
		for (VoteEntity voteEntity : voteEntities) {
			VoteDto voteDto = getVoteService.execute(voteEntity, userId);
			votes.add(voteDto);
		}
		boolean isLast = voteEntities.isLast();

		return GetVotesResponse.MainAndFinishPage.convert(votes, isLast);
	}

	private Slice<VoteEntity> getVoteListByRequest(Active active, Category category, Sort sort) {
		if (Active.isContinueRequest(active)) {
			return findContinueVotes(category, sort);
		}
		return findCompleteVotes(category, sort);
	}

	private Slice<VoteEntity> findCompleteVotes(Category category, Sort sort) {
		if (Category.isTotalRequest(category)) {
			return completeOrderBySort(sort);
		}
		return completeByCategoryOrderBySort(category, sort);
	}

	private Slice<VoteEntity> completeOrderBySort(Sort sort) {
		Pageable pageable = PageRequest.of(page, size);
		LocalDateTime now = LocalDateTime.now();

		if (Sort.isCurrentRequest(sort)) {
			return voteRepository.findAllFinishVotesOrderByCreatedDate(now, pageable);
		}
		return voteRepository.findAllFinishVotesOrderByVoteTotalCount(now, pageable);
	}

	private Slice<VoteEntity> completeByCategoryOrderBySort(Category category, Sort sort) {
		Pageable pageable = PageRequest.of(page, size);

		LocalDateTime now = LocalDateTime.now();

		if (Sort.isCurrentRequest(sort)) {
			return voteRepository.findAllFinishVotesByCategoryOrderByCreatedDate(now, category, pageable);
		}
		return voteRepository.findAllFinishVotesByCategoryOrderByVoteTotalCount(
				now, category, pageable);
	}

	private Slice<VoteEntity> findContinueVotes(Category category, Sort sort) {
		if (Category.isTotalRequest(category)) {
			return continueOrderBySort(sort);
		}
		return continueByCategoryOrderBySort(category, sort);
	}

	private Slice<VoteEntity> continueOrderBySort(Sort sort) {
		Pageable pageable = PageRequest.of(page, size);
		LocalDateTime now = LocalDateTime.now();

		if (Sort.isCurrentRequest(sort)) {
			return voteRepository.findAllContinueVotesOrderByCreatedDate(now, pageable);
		}
		return voteRepository.findAllContinueVotesOrderByVoteTotalCount(now, pageable);
	}

	private Slice<VoteEntity> continueByCategoryOrderBySort(Category category, Sort sort) {
		Pageable pageable = PageRequest.of(page, size);
		LocalDateTime now = LocalDateTime.now();

		if (Sort.isCurrentRequest(sort)) {
			return voteRepository.findAllContinueVotesByCategoryOrderByCreatedDate(
					now, category, pageable);
		}
		return voteRepository.findAllContinueVotesByCategoryOrderByVoteTotalCount(
				now, category, pageable);
	}
}
