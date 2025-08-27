package com.AutoConnect.AutoConnect.DTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "Le email est obligatoire")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "l email n est pas valid"
    )

    private String email;
    @NotBlank(message = "Le nom est obligatoire")
    private String name;
    @NotBlank(message = "Le prenom est obligatoire")
    private String surname;
    @NotBlank(message = "Le numero de telephone est obligatoire")
    private String phone;
    @NotNull
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Le mot de passe doit contenir au minimum 8 caractères, avec au moins une majuscule, une minuscule, un chiffre et un caractère spécial"
    )
    private String password;
    private String siren;

}
