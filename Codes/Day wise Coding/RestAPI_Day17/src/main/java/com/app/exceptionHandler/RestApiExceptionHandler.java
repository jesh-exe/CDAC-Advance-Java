package com.app.exceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.dto.ApiResponse;


/*
 *	@RestControllerAdvice is a annotation for SC to tell that if exception occurs, come in this class
 *	to get the exception Handling Methods  
 */

@RestControllerAdvice

public class RestApiExceptionHandler {

	public RestApiExceptionHandler() {
		System.out.println("In Rest API Exception Handler Contructor!");
	}

	/*
	 * @ExceptionHandler is used on method level with input as a Exception class whichever we wish
	 * to handle
	 * For example we have written Method Argument Not Found Exception, this will be thrown when
	 * Valid Tags are not validated properly at Request Body level
	 * 
	 * Return type will be always a ResponseEntity because we want to send ApiResponse obj as well as
	 * Status code with header to the Front end
	 * 
	 * If we wish to avoid the headache of ResponseEntity and just simple ApiResponse, then we will be using
	 * @ResponseStatus annotations with value as HttpStatus ENUM constant
	 * 
	 */
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
		System.out.println("Method Argument Not Valid Exception");
		
		/*
		 * As we know many of @Validations might get voilated by user, so to send all the error message
		 * we take them in the list of FieldError class which contains all the mappings of errors on 
		 * particular property
		 * So to just send on what field voilation done and what is the message, we put it in the map
		 * of String and String and add one by one Field name and message in map using API of FieldError
		 * getField and getDefaultMessage
		 */
		
		List<FieldError> fieldErrors = e.getFieldErrors();
		Map<String, String> mp = new HashMap<String, String>();
		for (FieldError fe : fieldErrors)
			mp.put(fe.getField(), fe.getDefaultMessage());
		return new ResponseEntity<>(mp, HttpStatus.NOT_FOUND);
	}
	
	/*
	 * Here we have used @Response Status so a status with ApiResponse will be sent to Frontend
	 */

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiResponse handleRuntimeException(RuntimeException e) {
		System.out.println("In Runtime Handler");
		return new ApiResponse(e.getMessage());
	}

}
