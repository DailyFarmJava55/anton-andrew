package telran.dailyfarm.auth.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsExcepsion extends RuntimeException {
	private static final long serialVersionUID = -3484828573234712995L;
}
