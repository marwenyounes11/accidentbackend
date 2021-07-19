package io.oa.accidentincident.exception;



import java.net.ConnectException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);


	private MessageSource messageSource;

	@Autowired
	public ExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	@ResponseBody
	public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Object errorResponse = new ErrorResponse(RestExceptionCode.METHOD_NOT_FOUND,
				this.messageSource.getMessage("message.method.not.found",
    			new Object[] { },
                        Locale.FRENCH), null);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(RestException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleRestException(RestException ex) {
		if (ex.getExceptionCode().isLogError()) {
			LOGGER.error("Handle Pharmacie exception", ex);
		}
		ErrorResponse errorResponse = new ErrorResponse(ex);
		return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
	}

//	@org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
//	@ResponseBody
//	public ResponseEntity<ErrorResponse> handleBadCredentials(Exception ex) {
//		ErrorResponse errorResponse = new ErrorResponse(RestExceptionCode.AUTHORIZATION_REQUIRED,RestExceptionCode.AUTHORIZATION_REQUIRED.getError(), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//	}
//
//	@org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
//	@ResponseBody
//	public ResponseEntity<ErrorResponse> handleAccessDenied(Exception ex) {
//		ErrorResponse errorResponse = new ErrorResponse(RestExceptionCode.ACCESS_DENIED,RestExceptionCode.ACCESS_DENIED.getError(), null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//	}

	@org.springframework.web.bind.annotation.ExceptionHandler({ CannotGetJdbcConnectionException.class,
			CannotCreateTransactionException.class, ConnectException.class, DataAccessResourceFailureException.class })
	@ResponseBody
	public ResponseEntity<ErrorResponse> databaseNotPresent(Exception ex) {
		LOGGER.error("No connection to database");
		ErrorResponse errorResponse = new ErrorResponse(RestExceptionCode.SERVICE_UNAVAILABLE,this.messageSource.getMessage("message.service.unavailable",
    			new Object[] { },Locale.FRENCH), null);
		return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(SQLException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> sqlError(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(RestExceptionCode.INTERNAL_SERVER_ERROR,this.messageSource.getMessage("message.server.internal.error",
    			new Object[] { },Locale.FRENCH), null);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// @Override
	// protected ResponseEntity<Object>
	// handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	// HttpHeaders headers, HttpStatus status, WebRequest request) {
	// List<String> errors = new ArrayList<String>();
	// for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	// errors.add(error.getField() + ": " + error.getDefaultMessage());
	// }
	//
	// ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
	// ex.getLocalizedMessage(), errors);
	// return handleExceptionInternal(ex, apiError, headers,
	// apiError.getStatus(), request);
	// }
	// @Override
	// public ResponseEntity<Object>
	// handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	// HttpHeaders headers,
	// HttpStatus status, WebRequest request) {
	// List<Map<String, String>> errorsList = new ArrayList<Map<String,
	// String>>();
	// Map<String, Object> response = new HashMap<>();
	// String fields = "";
	// for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
	// Map<String, String> error = new HashMap<>();
	// error.put("field", fieldError.getField());
	// error.put("message", fieldError.getDefaultMessage());
	// if (fields.equals("")) {
	// fields = fieldError.getField();
	// } else {
	// fields += ", " + fieldError.getField();
	// }
	// errorsList.add(error);
	// }
	//
	// response.put("error", fields);
	// response.put("code", RestExceptionCode.MISSING_REQUIRED_FIELDS.name());
	// response.put("message",
	// this.gMessageByLocaleService.getMessage(RestExceptionCode.REQUIRED_FIELDS_MISSING.getError()));
	// // return errors;
	// return new ResponseEntity<Object>(response, new HttpHeaders(),
	// HttpStatus.FORBIDDEN);
	// }

	@Override
	@ResponseBody
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		logger.info("ex.getCause()" + ex.getCause().toString());
		String message = ex.getMostSpecificCause().getMessage().replaceAll("\n", "");
		int i1 = message.indexOf("at [Source:", 0);
		int i2 = message.indexOf("[\"", 0);
		String error = message.substring(0, i1) + "for the parameter " + message.substring(i2, message.length() - 1);

		Map<String, Object> response = new HashMap<>();
		response.put("error", error);
		response.put("code", RestExceptionCode.NON_VALID_FIELDS.name());
		response.put("message", this.messageSource.getMessage(RestExceptionCode.NON_VALID_FIELDS.getError(),new Object[] { },Locale.FRENCH));
		// return errors;
		return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute("javax.servlet.error.exception", ex, RequestAttributes.SCOPE_REQUEST);
		}

		ErrorResponse errorResponse;
		if (ex instanceof RestException) {
			LOGGER.trace("eDentOM exception", ex);
			errorResponse = new ErrorResponse((RestException) ex);
		} else {
			LOGGER.error("Internal server error", ex);
			errorResponse = new ErrorResponse(RestExceptionCode.INTERNAL_SERVER_ERROR,this.messageSource.getMessage(RestExceptionCode.INTERNAL_SERVER_ERROR.getError(),new Object[] { },Locale.FRENCH), null);
		}

		//return new ResponseEntity<>(errorResponse, headers, status);
		return new ResponseEntity<Object>(errorResponse, status);
	}
}
