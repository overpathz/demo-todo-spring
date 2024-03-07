package com.example.demoreplay.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запит на автентифікацію")
public class SignInRequest {
    @Schema(description = "Імя користувача", example = "John4")
    @Size(min = 5, max = 50, message = "Логін від 5 до 50 символів")
    @NotBlank(message = "Логін не має бути пустим")
    private String username;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "Довжина паролю має бути від 8 до 255 символів")
    @NotBlank(message = "Пароль не має бути пустим")
    private String password;
}