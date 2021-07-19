package io.oa.accidentincident.exception;



public class ErrorResponse {

	private RestExceptionCode code;
	private String message;
	private String error;
	
	public RestExceptionCode getCode() {
		return code;
	}

	public void setCode(RestExceptionCode code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public ErrorResponse(RestExceptionCode code, String message, String error) {
		super();
		this.code = code;
		this.message = message;
		this.error = error;
	}

	public ErrorResponse(RestException ex) {
		super();
		this.code = ex.getExceptionCode();
	} 
	public ErrorResponse(RestExceptionCode code) {
		super();
		this.code = code;
	}

}
