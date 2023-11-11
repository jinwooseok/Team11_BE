package com.kakao.golajuma.auth.domain.service;

import com.kakao.golajuma.auth.domain.exception.DuplicatedEmailException;
import com.kakao.golajuma.auth.persistence.repository.UserRepository;
import com.kakao.golajuma.auth.web.supplier.EmailSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ValidEmailService {
	private final UserRepository userRepository;

	public void execute(EmailSupplier supplier) {
		boolean exists = userRepository.existsByEmail(supplier.getEmail());
		if (exists) {
			throw new DuplicatedEmailException();
		}
	}
}
