package larionovoleksanr.exam.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDeviceDTO(
        @NotEmpty
        @Size(min = 2, max = 20, message = "Il Cognome deve contenere tra 2 e 20 caratteri")
        String type,
        @NotEmpty
        @Size(min = 2, max = 20, message = "Il Cognome deve contenere tra 2 e 20 caratteri")
        String stateOfDevice,
        Long idEmployee
) {
}
