package com.org.loanledger.service;

import com.org.loanledger.request.Request;

public interface LedgerService {

	boolean bookLoan(Request request) throws Exception;

	boolean makePayment(Request request) throws Exception;

	String checkBalance(Request request) throws Exception;

}
