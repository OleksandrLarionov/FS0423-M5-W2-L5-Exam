package larionovoleksanr.exam.payloads;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record ErrorsPayloadWhitList(String message, Date date, List<String> errorsList) {
}
