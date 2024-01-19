package larionovoleksanr.exam.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewEmployeeDTO(
        @Email
        String email,
        @NotEmpty
        @Size(min = 2, max = 20, message = "Il Nome deve contenere tra 2 e 20 caratteri")
        String name,
        @NotEmpty
        @Size(min = 2, max = 20, message = "Il Cognome deve contenere tra 2 e 20 caratteri")
        String surname) {
}
