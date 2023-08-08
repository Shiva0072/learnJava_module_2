package com.org.loanledger.request;

import com.org.loanledger.enums.RequestType;
import com.org.loanledger.exceptions.IllegalFieldAccessException;
import com.org.loanledger.exceptions.IllegalFieldValueException;

public class LumpsumPaymentRequest extends AbstractRequest {

	private long lumpsumAmount;
	private int emiNumber;

	public LumpsumPaymentRequest(Builder b) {
		super(b);
		this.lumpsumAmount = b.lumpsumAmount;
		this.emiNumber = b.emiNumber;
	}

	public static class Builder extends AbstractRequest.Builder<LumpsumPaymentRequest, Builder> {
		private long lumpsumAmount;
		private int emiNumber;

		public Builder() {
			super(RequestType.LUMPSUM_PAYMENT);
		}

		@Override
		public Builder self() {
			return this;
		}

		public Builder lumpsumAmount(long lumpsumAmount) {
			if (lumpsumAmount <= 0) {
				throw new IllegalFieldValueException("payment amount should be positive");
			}
			this.lumpsumAmount = lumpsumAmount;
			return self();
		}

		public Builder emiNumber(int emiNumber) {
			if (emiNumber < 0) {
				throw new IllegalFieldValueException("emi no should not be negative");
			}
			this.emiNumber = emiNumber;
			return self();
		}

		@Override
		public LumpsumPaymentRequest build() {

			return new LumpsumPaymentRequest(this);
		}

	}

	public long getLumpsumAmount() {
		return lumpsumAmount;
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
}
