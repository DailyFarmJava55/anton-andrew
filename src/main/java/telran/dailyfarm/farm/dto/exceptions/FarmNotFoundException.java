package telran.dailyfarm.farm.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FarmNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4999953775857981069L;

}
