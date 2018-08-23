package com.rulemig.error;

public class RuleMigException extends Exception {
	
	private static final long serialVersionUID = 3851328517635045551L;

	public RuleMigException(String errorCode, String message) {
		super("[" + errorCode +"] - " + message);
	}
	
	public RuleMigException(String errorCode, String message, Throwable cause) {
		this(errorCode, message);
		initCause(cause);
	}
}
