package larionovoleksanr.exam.payloads;


import java.util.List;

public record ErrorsPayloadWhitList(String message, String date, List<String> errorsList) {
}
