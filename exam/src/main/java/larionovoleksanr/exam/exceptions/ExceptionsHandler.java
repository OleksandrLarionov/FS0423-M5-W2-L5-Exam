package larionovoleksanr.exam.exceptions;

import larionovoleksanr.exam.payloads.ErrorsPayloadWhitList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class ExceptionsHandler {
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorsPayloadWhitList handleBadRequest(BadRequestException ex) {

		Locale locale = new Locale("it", "IT");
		DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
		String time = dateFormat.format(new Date());

		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());

		List<String> errorsMessages = new ArrayList<>();
		if(ex.getErrorList() != null)
			errorsMessages = ex.getErrorList().stream().map(errore -> errore.getDefaultMessage()).toList();
		return new ErrorsPayloadWhitList(ex.getMessage(), date + "_ at _" + time, errorsMessages);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorsPayload handleNotFound(NotFoundException ex) {
		return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorsPayload handleGenericError(Exception ex) {
		ex.printStackTrace();
		return new ErrorsPayload("Un po di pazienza ci stiamo lavorando", LocalDateTime.now());
	}
}
