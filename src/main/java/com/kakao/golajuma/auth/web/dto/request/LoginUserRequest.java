package com.kakao.golajuma.auth.web.dto.request;

import com.kakao.golajuma.auth.web.supplier.EmailSupplier;
import com.kakao.golajuma.common.marker.AbstractRequestDto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class LoginUserRequest implements AbstractRequestDto, EmailSupplier {

	@NotBlank(message = ValidExceptionMessage.EMPTY_MESSAGE)
	@Email(message = ValidExceptionMessage.EMAIL_FORMAT_MESSAGE)
	private String email;

	@NotBlank(message = ValidExceptionMessage.EMAIL_FORMAT_MESSAGE)
	@Size(min = 8)
	private String password;
}
