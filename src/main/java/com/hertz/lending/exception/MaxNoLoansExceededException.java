package com.hertz.lending.exception;

public class MaxNoLoansExceededException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public MaxNoLoansExceededException() {
		super("Maximum number of loans have been exceeded for this person");
	}

}
