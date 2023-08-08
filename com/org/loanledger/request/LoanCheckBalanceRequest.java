package com.org.loanledger.request;

import com.org.loanledger.enums.RequestType;
import com.org.loanledger.exceptions.IllegalFieldAccessException;
import com.org.loanledger.exceptions.IllegalFieldValueException;

public class LoanCheckBalanceRequest extends AbstractRequest {

	private int emiNumber;

	public LoanCheckBalanceRequest(Builder b) {
		super(b);
		this.emiNumber = b.emiNumber;

	}

	public static class Builder extends AbstractRequest.Builder<LoanCheckBalanceRequest, Builder> {

		private int emiNumber;

		public Builder() {
			super(RequestType.BALANCE);
		}

		@Override
		public Builder self() {
			return this;
		}

		public Builder emiNumber(int emiNumber) {
			if (emiNumber < 0) {
				throw new IllegalFieldValueException("emi no should not be negative");
			}
			this.emiNumber = emiNumber;
			return self();
		}

		@Override
		public LoanCheckBalanceRequest build() {

			return new LoanCheckBalanceRequest(this);
		}

	}

	public int getEmiNumber() {
		return emiNumber;
	}

	@Override
	public long getLoanAmount() {
		throw new IllegalFieldAccessException("method not supported");
	}

	@Override
	public int getTenureInyears() {
		throw new IllegalFieldAccessException("method not supported");
	}

	@Override
	public double getRateOfInterest() {
		throw new IllegalFieldAccessException("method not supported");
	}

	@Override
	public long getLumpsumAmount() {
		throw new IllegalFieldAccessException("method not supported");
	}

}
