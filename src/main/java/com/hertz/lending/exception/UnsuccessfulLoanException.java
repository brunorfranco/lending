package com.hertz.lending.exception;

public class UnsuccessfulLoanException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsuccessfulLoanException() {
		super("You are not eligible to loan any more books");
	}

}
