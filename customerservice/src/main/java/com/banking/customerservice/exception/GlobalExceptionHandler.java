package com.banking.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(DuplicateCustomerException.class)
    public ResponseEntity<String> handleAppException(DuplicateCustomerException ex) {
    	
    	logger.warn("Duplicate customer error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body(ex.getMessage());
    } 
    
    @ExceptionHandler(DataSaveException.class)
    public ResponseEntity<String> handleAppException(DataSaveException ex) {
    	
    	logger.warn("Data Save error: {}", ex.getMessage());
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericError(Exception ex) {
        logger.error("Unexpected error occurred", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}
