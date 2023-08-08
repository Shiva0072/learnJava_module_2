package com.org.loanledger.request;

import com.org.loanledger.enums.RequestType;
import com.org.loanledger.exceptions.IllegalFieldValueException;

public abstract class AbstractRequest implements Request {
	protected RequestType requestType;
	protected String bankName;
	protected String customerName;

	public AbstractRequest(Builder<?, ?> b) {
		this.requestType = b.requestType;
		this.customerName = b.customerName;
		this.bankName = b.bankName;
	}

	public static abstract class Builder<E extends AbstractRequest, T extends Builder<E, T>> {

		private RequestType requestType;
		private String bankName;
		private String customerName;

		public abstract T self();

		public abstract E build();

		public Builder(RequestType requestType) {
			this.requestType = requestType;
		}

		public T bankName(String bankName) {
			if (bankName == null || bankName.length() == 0) {
				throw new IllegalFieldValueException("bank name should not be empty");
			}
			this.bankName = bankName;
			return self();
		}

		public T customerName(String customerName) {
			if (customerName == null || customerName.length() == 0) {
				throw new IllegalFieldValueException("customer name should not be empty");
			}
			this.customerName = customerName;
			return self();
		}

	}

	public RequestType getRequestType() {
		return requestType;
	}

	public String getBankName() {
		return bankName;
	}

	public String getCustomerName() {
		return customerName;
	}

}
