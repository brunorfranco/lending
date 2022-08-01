package com.hertz.lending.exception;

public class OutstandingReturnException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public OutstandingReturnException() {
		super("You have outstanding returns - Loan denied");
	}

}
