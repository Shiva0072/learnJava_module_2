package com.org.loanledger.factory;

import com.org.loanledger.request.LoanBookRequest;
import com.org.loanledger.request.LoanCheckBalanceRequest;
import com.org.loanledger.request.LumpsumPaymentRequest;
import com.org.loanledger.request.AbstractRequest;

public class RequestFactory {
	public static AbstractRequest getRequest(String requestString) {

		String[] args = requestString.split(" ");

		switch (args[0]) {
			case "LOAN":
				return new LoanBookRequest.Builder().bankName(args[1]).customerName(args[2])
						.loanAmount(Long.valueOf(args[3])).tenureInyears(Integer.valueOf(args[4]))
						.rateOfInterest(Double.valueOf(args[5])).build();
			case "PAYMENT":
				return new LumpsumPaymentRequest.Builder().bankName(args[1]).customerName(args[2])
						.lumpsumAmount(Long.valueOf(args[3])).emiNumber(Integer.valueOf(args[4])).build();
			case "BALANCE":
				return new LoanCheckBalanceRequest.Builder().bankName(args[1]).customerName(args[2])
						.emiNumber(Integer.valueOf(args[3])).build();
			default:
				throw new IllegalArgumentException("Unexpected request arguments: ");
		}
	}

}
