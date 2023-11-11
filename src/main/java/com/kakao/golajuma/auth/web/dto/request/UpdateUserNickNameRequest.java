package com.kakao.golajuma.auth.web.dto.request;

import com.kakao.golajuma.auth.web.supplier.NicknameSupplier;
import com.kakao.golajuma.common.marker.AbstractRequestDto;
import javax.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class UpdateUserNickNameRequest implements AbstractRequestDto, NicknameSupplier {
	@NotBlank(message = ValidExceptionMessage.EMPTY_MESSAGE)
	private String nickname;
}
