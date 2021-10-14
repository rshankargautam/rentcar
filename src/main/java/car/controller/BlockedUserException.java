package car.controller;

public class BlockedUserException extends RuntimeException {

	public BlockedUserException(String message) {
		super(message);
	}

	public BlockedUserException(Throwable cause) {
		super(cause);
	}

	public BlockedUserException(String message, Throwable cause) {
		super(message, cause);
	}

}
