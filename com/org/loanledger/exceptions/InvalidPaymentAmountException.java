package com.org.loanledger.exceptions;

public class InvalidPaymentAmountException extends Exception {

	private static final long serialVersionUID = -2248821388759795795L;


	public InvalidPaymentAmountException(String message) {
		super(message);
	}

}
