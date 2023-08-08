package com.org.loanledger.entity;

import java.util.UUID;

import com.org.loanledger.enums.LoanStatus;

public class Loan implements Entity {
	private UUID loanId;
	private String customerName;
	private String bankName;
	private long loanAmount;
	private int loanTenureInYears;
	private double rateOfInterest;
	private long repaymentAmount;
	private long emiAmount;
	private LoanStatus loanStatus;

	public Loan(Builder b) {
		this.loanId = b.loanId;
		this.customerName = b.customerName;
		this.bankName = b.bankName;
		this.loanAmount = b.loanAmount;
		this.loanTenureInYears = b.loanTenureInYears;
		this.rateOfInterest = b.rateOfInterest;
		this.repaymentAmount = b.repaymentAmount;
		this.emiAmount = b.emiAmount;
		this.loanStatus = b.loanStatus;
	}

	public static class Builder {
		private UUID loanId;
		private String customerName;
		private String bankName;
		private long loanAmount;
		private double rateOfInterest;
		private int loanTenureInYears;
		private long repaymentAmount;
		private long emiAmount;
		private LoanStatus loanStatus;

		public Builder() {

		}

		public Builder loanId(UUID loanId) {
			this.loanId = loanId;
			return this;
		}

		public Builder customerName(String customerName) {
			this.customerName = customerName;
			return this;
		}

		public Builder bankName(String bankName) {
			this.bankName = bankName;
			return this;
		}

		public Builder loanAmount(long loanAmount) {
			this.loanAmount = loanAmount;
			return this;
		}

		public Builder loanTenureInYears(int loanTenureInYears) {
			this.loanTenureInYears = loanTenureInYears;
			return this;
		}

		public Builder rateOfInterest(double rateOfInterest) {
			this.rateOfInterest = rateOfInterest;
			return this;
		}

		public Builder repaymentAmount(long repaymentAmount) {
			this.repaymentAmount = repaymentAmount;
			return this;
		}

		public Builder emiAmount(long emiAmount) {
			this.emiAmount = emiAmount;
			return this;
		}

		public Builder loanStatus(LoanStatus loanStatus) {
			this.loanStatus = loanStatus;
			return this;
		}

		public Loan build() {
			return new Loan(this);
		}
	}

	public UUID getLoanId() {
		return loanId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getBankName() {
		return bankName;
	}

	public long getLoanAmount() {
		return loanAmount;
	}

	public int getLoanTenureInYears() {
		return loanTenureInYears;
	}

	public double getRateOfInterest() {
		return rateOfInterest;
	}

	public long getRepaymentAmount() {
		return repaymentAmount;
	}

	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}

	public long getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(long emiAmount) {
		this.emiAmount = emiAmount;
	}

	public void setRepaymentAmount(long repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public String getKey() {
		return bankName + "-" + customerName;
	}

}
