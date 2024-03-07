package com.example.demoreplay.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {

    @Schema(description = "Імя користувача", example = "Jon")
    @Size(min = 5, max = 50, message = "Логін від 5 до 50 символів")
    @NotBlank(message = "Логін не має бути пустим")
    private String username;

    @Schema(description = "Еммейл", example = "jondoe@gmail.com")
    @Size(min = 5, max = 255, message = "Емейл має бути від 5 до 255 символів")
    @NotBlank(message = "Емейл не має бути пустим")
    @Email(message = "Емейл має бути формату: user@example.com")
    private String email;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "Довжина паролю має бути від 8 до 255 символів")
    @NotBlank(message = "Пароль не має бути пустим")
    private String password;
}