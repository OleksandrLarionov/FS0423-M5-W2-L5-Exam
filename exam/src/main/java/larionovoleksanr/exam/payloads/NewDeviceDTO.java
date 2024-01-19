package larionovoleksanr.exam.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDeviceDTO(
        @NotEmpty
        @Size(min = 2, max = 50, message = "Il nome del device deve essere compreso tra 2 e 50 caratteri")
        String deviceType,
        @NotEmpty
        @Size(min = 8, max = 12, message = "disponibile , assegnato, in manutenzione, dismesso")
        String stateOfDevice,
        Long idEmployee
) {
}
