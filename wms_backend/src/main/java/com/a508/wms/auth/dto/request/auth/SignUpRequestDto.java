package com.a508.wms.auth.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-z0-9]{8,13}$")
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String nickName;

}
