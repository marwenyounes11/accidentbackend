package io.oa.accidentincident.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class LieuxAccidentIntrouvableException extends RuntimeException{
	public LieuxAccidentIntrouvableException(String s) {
        super(s);
    }
}
