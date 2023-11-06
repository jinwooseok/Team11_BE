package com.kakao.golajuma.auth.web.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UpdateUserNickNameRequest {
	@NotBlank private String nickname;
}
