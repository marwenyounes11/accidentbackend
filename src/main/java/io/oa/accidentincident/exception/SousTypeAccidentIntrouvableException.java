package io.oa.accidentincident.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class SousTypeAccidentIntrouvableException extends RuntimeException{
	public SousTypeAccidentIntrouvableException(String s) {
        super(s);
    }
}
