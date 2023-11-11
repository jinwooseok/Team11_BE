package com.kakao.golajuma.auth.web.dto.request;

import com.kakao.golajuma.auth.web.supplier.EmailSupplier;
import com.kakao.golajuma.auth.web.supplier.NicknameSupplier;
import com.kakao.golajuma.common.marker.AbstractRequestDto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SaveUserRequest implements AbstractRequestDto, EmailSupplier, NicknameSupplier {
	@NotBlank(message = ValidExceptionMessage.EMPTY_MESSAGE)
	private String nickname;

	@NotBlank(message = ValidExceptionMessage.EMPTY_MESSAGE)
	@Email
	private String email;

	@NotBlank(message = ValidExceptionMessage.EMPTY_MESSAGE)
	private String password;
}
