package com.org.loanledger.request;

import com.org.loanledger.enums.RequestType;

public interface Request {

	RequestType getRequestType();

	String getBankName();

	String getCustomerName();

	long getLoanAmount();

	int getTenureInyears();

	double getRateOfInterest();

	int getEmiNumber();

	long getLumpsumAmount();

}
