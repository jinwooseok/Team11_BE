package com.kakao.golajuma.auth.web.dto.request;

import com.kakao.golajuma.auth.web.supplier.EmailSupplier;
import com.kakao.golajuma.common.marker.AbstractRequestDto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class UpdateUserEmailRequest implements AbstractRequestDto, EmailSupplier {
	@NotBlank(message = ValidExceptionMessage.EMPTY_MESSAGE)
	@Email(message = ValidExceptionMessage.EMAIL_FORMAT_MESSAGE)
	private String email;
}
