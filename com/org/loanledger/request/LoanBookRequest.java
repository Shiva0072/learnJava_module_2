package com.org.loanledger.request;

import com.org.loanledger.enums.RequestType;
import com.org.loanledger.exceptions.IllegalFieldAccessException;
import com.org.loanledger.exceptions.IllegalFieldValueException;

public class LoanBookRequest extends AbstractRequest {

	private long loanAmount;
	private int tenureInyears;
	private double rateOfInterest;

	public LoanBookRequest(Builder b) {
		super(b);
		this.loanAmount = b.loanAmount;
		this.tenureInyears = b.tenureInyears;
		this.rateOfInterest = b.rateOfInterest;
	}

	public static class Builder extends AbstractRequest.Builder<LoanBookRequest, Builder> {
		private long loanAmount;
		private int tenureInyears;
		private double rateOfInterest;

		public Builder() {
			super(RequestType.LOAN);
		}

		@Override
		public Builder self() {
			return this;
		}

		public Builder loanAmount(long loanAmount) {
			if (loanAmount <= 0) {
				throw new  IllegalFieldValueException("loan amount should be greate than 0");
			}
			this.loanAmount = loanAmount;
			return self();
		}

		public Builder tenureInyears(int tenureInyears) {
			if (tenureInyears <= 0) {
				throw new IllegalFieldValueException("loan tenure should be greater than 0");
			}
			this.tenureInyears = tenureInyears;
			return self();
		}

		public Builder rateOfInterest(double rateOfInterest) {
			if (rateOfInterest < 0) {
				throw new IllegalFieldValueException("rate of interest should be positive");
			}
			this.rateOfInterest = rateOfInterest;
			return self();
		}

		@Override
		public LoanBookRequest build() {

			return new LoanBookRequest(this);
		}

	}

	public long getLoanAmount() {
		return loanAmount;
	}

	public int getTenureInyears() {
		return tenureInyears;
	}

	public double getRateOfInterest() {
		return rateOfInterest;
	}

	@Override
	public int getEmiNumber() {
		throw new IllegalFieldAccessException("method not supported");
	}

	@Override
	public long getLumpsumAmount() {
		throw new IllegalFieldAccessException("method not supported");
	}

}
