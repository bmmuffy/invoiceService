package za.co.digitalplatoon.invoiceservice.invoice.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomException extends RuntimeException {
	public CustomException(String exception) {
		super(exception);
	}

}
