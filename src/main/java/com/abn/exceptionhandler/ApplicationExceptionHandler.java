package com.abn.exceptionhandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.abn.exception.InvalidDateFormatException;
import com.abn.exception.NoDataFoundException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
	
	@ExceptionHandler(value = NoDataFoundException.class)
	public ResponseEntity<?> handleNoDataFoundException(NoDataFoundException ndfe){
		
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "Failed there is no data");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", ndfe.getMessage());
		errorbody.put("statuscode", "404");
		
		return new ResponseEntity<>(errorbody, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvalidDateFormatException.class)
	public ResponseEntity<?> handleInvalidDateFormatException(InvalidDateFormatException idfe){
		logger.error("** handleInvalidDateFormatException() - Execution started **");
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "Invalid date format");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", idfe.getMessage());
		
		return new ResponseEntity<>(errorbody, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(NullPointerException npe){
		logger.error("** handleNullPointerException() - Execution started **");
		Map<String, Object> errorbody = new LinkedHashMap<>();
		errorbody.put("error", "null exception occured");
		errorbody.put("timestamp", LocalDateTime.now());
		errorbody.put("details", npe.getMessage());
		
		return new ResponseEntity<>(errorbody, HttpStatus.NOT_FOUND);
	}

}
