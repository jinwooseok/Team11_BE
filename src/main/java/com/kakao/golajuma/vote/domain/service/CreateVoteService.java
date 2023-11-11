package com.kakao.golajuma.vote.domain.service;

import com.kakao.golajuma.vote.persistence.entity.OptionEntity;
import com.kakao.golajuma.vote.persistence.entity.VoteEntity;
import com.kakao.golajuma.vote.persistence.repository.OptionRepository;
import com.kakao.golajuma.vote.persistence.repository.VoteRepository;
import com.kakao.golajuma.vote.util.ImageUploader;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import com.kakao.golajuma.vote.web.dto.response.CreateVoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CreateVoteService {

	private final VoteRepository voteRepository;
	private final OptionRepository optionRepository;
	private final ImageUploader imageUploader;

	public CreateVoteResponse execute(CreateVoteRequest request, Long userId) {
		VoteEntity voteEntity = VoteEntity.create(request, userId);
		Long voteId = voteRepository.save(voteEntity).getId();
		for (CreateVoteRequest.OptionDto optionDto : request.getOptions()) {
			OptionEntity optionEntity = createOption(optionDto, voteId);
			optionRepository.save(optionEntity);
		}
		return CreateVoteResponse.convert(voteId);
	}

	private OptionEntity createOption(CreateVoteRequest.OptionDto optionDto, Long voteId) {
		if (optionDto.getImage() != null) {
			String imagePath = imageUploader.uploadImageByBase64(optionDto);
			return OptionEntity.createWithImage(optionDto, imagePath, voteId);
		}
		return OptionEntity.create(optionDto, voteId);
	}
}
