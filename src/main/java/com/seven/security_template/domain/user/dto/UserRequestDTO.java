package com.seven.security_template.domain.user.dto;

import com.seven.security_template.domain.user.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * @NotBlanknull이 아니고, 공백을 제외한 길이가 0보다 커야 함 (문자열 전용)
 * @NotNullnull이 아니어야 함
 * @Min / @Max지정된 숫자 이상/이하이어야 함
 * @Email이메일 형식이어야 함
 * @Pattern정규표현식에 맞아야 함 (비밀번호 복잡도 체크 등)
 */
@Getter @Setter
public class UserRequestDTO {

    @NotBlank(message = "아이디는 필수입니다.")
    @Size(min = 4, max = 20, message = "아이디는 4~20자 사이여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    private UserRole role;
}
