package com.SpringBoot.WebBlog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {

    private Long id;

    @NotEmpty(message = "Ad boş bırakılmamalıdır.")
    private String firstName;

    @NotEmpty(message = "Soyadı boş bırakılmamalıdır")
    private String lastName;

    @NotEmpty(message = "E-posta boş olmamalıdır")
    @Email
    private String email;

    @NotEmpty(message = "Şifre boş olmamalıdır")
    private String password;
}