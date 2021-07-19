package io.oa.accidentincident.exception;



import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException {

	private static final long serialVersionUID = -1099091914203779518L;

	private RestExceptionCode exceptionCode;
	private HttpStatus httpStatus;
	private String error;
	private String message;

	public RestException(RestExceptionCode exceptionCode) {
		super(exceptionCode.getError());
		this.exceptionCode = exceptionCode;
		this.httpStatus = exceptionCode.getHttpStatus();
		this.error = exceptionCode.getError();
	}

	public RestException(String message, RestExceptionCode exceptionCode) {
		super(exceptionCode.getError());
		this.exceptionCode = exceptionCode;
		this.error = message;
		 this.message = message;
		this.httpStatus = exceptionCode.getHttpStatus();
	}

	public RestException(String message, RestExceptionCode exceptionCode, HttpStatus httpStatus) {
		this.message = message;
		this.exceptionCode = exceptionCode;
		this.httpStatus = httpStatus;
	}

	public RestExceptionCode getExceptionCode() {
		return exceptionCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public String getError() {
		return error;
	}

	@Override
	public String toString() {
		return "RestException [exceptionCode=" + exceptionCode + ", httpStatus=" + httpStatus + ", error=" + error
				+ ", message=" + message + "]";
	}

}