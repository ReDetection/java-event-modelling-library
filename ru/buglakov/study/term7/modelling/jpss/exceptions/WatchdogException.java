package ru.buglakov.study.term7.modelling.jpss.exceptions;

public class WatchdogException extends RuntimeException {
	// TODO Auto-generated

	public WatchdogException() {
	}

	public WatchdogException(String message) {
		super(message);
	}

	public WatchdogException(Throwable cause) {
		super(cause);
	}

	public WatchdogException(String message, Throwable cause) {
		super(message, cause);
	}

	public WatchdogException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
