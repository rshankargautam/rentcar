package car.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CarRentalExceptionHandler {


	  @ExceptionHandler 
	  public ResponseEntity<CarRentalErrorResponse> handleException(InvalidUserException exc) { // create a StudentErrorResponse

	  CarRentalErrorResponse error = new CarRentalErrorResponse();
	  error.setStatus(HttpStatus.NOT_FOUND.value());
	  error.setMessage(exc.getMessage());
	  error.setTimeStamp(System.currentTimeMillis());

	  return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); }

	@ExceptionHandler
	public ResponseEntity<CarRentalErrorResponse> handleException(Exception exc) {

		CarRentalErrorResponse error = new CarRentalErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public ResponseEntity<CarRentalErrorResponse> handleException(BlockedUserException exc) {

		CarRentalErrorResponse error = new CarRentalErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
