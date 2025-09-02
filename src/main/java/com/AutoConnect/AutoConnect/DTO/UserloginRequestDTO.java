package com.AutoConnect.AutoConnect.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserloginRequestDTO {
    @NotBlank(message = "Le email est obligatoire")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "l email n est pas valide"
    )

    private String email;

    @NotNull
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Le mot de passe doit contenir au minimum 8 caractères, avec au moins une majuscule, une minuscule, un chiffre et un caractère spécial"
    )
    private String password;

}
