package com.kakao.golajuma.vote.util;

import com.kakao.golajuma.vote.domain.exception.ImageException;
import com.kakao.golajuma.vote.web.dto.request.CreateVoteRequest;
import java.io.*;
import java.util.Base64;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class ImageUploader {

	private static final String SYSTEM_PATH = System.getProperty("user.dir");
	private static final String UPLOAD_PATH =
			"/out/production/classes/com/kakao/golajuma/vote/infra/images/";

	public String uploadImageByBase64(CreateVoteRequest.OptionDto request) {
		String base64 = request.getImage();
		String fileName = request.getName();
		// 파일이 업로드되지 않았거나 사이즈가 큰 경우를 체크합니다.
		// 사이즈는 일반 바이트에서 1.33을 곱하면 BASE64 사이즈가 대략 나옵니다.
		if (base64 == null || base64.equals("")) {
			throw new ImageException("이미지가 null 입니다");
		}
		if (base64.length() > 400000) {
			throw new ImageException("이미지가 너무 큽니다");
		}

		try {
			// 저장할 파일 경로를 지정합니다.
			String changedName = UUID.randomUUID().toString() + "_" + fileName;
			String uploadPath = SYSTEM_PATH + UPLOAD_PATH;
			String storedPath = uploadPath + changedName;
			File file = new File(storedPath);

			// BASE64를 일반 파일로 변환하고 저장합니다.
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] decodedBytes = decoder.decode(base64.getBytes());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(decodedBytes);
			fileOutputStream.close();
			return storedPath;
		} catch (IOException e) {
			throw new ImageException("이미지 저장 실패");
		}
	}
}
