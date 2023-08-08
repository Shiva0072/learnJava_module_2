package com.org.loanledger.exceptions;

public class LoanNotExistException extends Exception {

	private static final long serialVersionUID = -2248821388759795795L;


	public LoanNotExistException(String message) {
		super(message);
	}

}
