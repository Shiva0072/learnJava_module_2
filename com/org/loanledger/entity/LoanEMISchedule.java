package com.org.loanledger.entity;

import java.util.UUID;

import com.org.loanledger.enums.EMIStatus;
import com.org.loanledger.enums.LoanStatus;

public class LoanEMISchedule {
	private UUID loanId;
	private int emiNumber;
	private long emiAmount;
	private long addOnLumpsum;
	private EMIStatus status;

	public LoanEMISchedule(Builder b) {
		this.loanId = b.loanId;
		this.emiNumber = b.emiNumber;
		this.emiAmount = b.emiAmount;
		this.addOnLumpsum = b.addOnLumpsum;
		this.status = b.status;
	}

	public static class Builder {
		private UUID loanId;
		private int emiNumber;
		private long emiAmount;
		private long addOnLumpsum;
		private EMIStatus status;

		public Builder() {

		}

		public Builder loanId(UUID loanId) {
			this.loanId = loanId;
			return this;
		}

		public Builder emiNumber(int emiNumber) {
			this.emiNumber = emiNumber;
			return this;
		}

		public Builder emiAmount(long emiAmount) {
			this.emiAmount = emiAmount;
			return this;
		}

		public Builder addOnLumpsum(long addOnLumpsum) {
			this.addOnLumpsum = addOnLumpsum;
			return this;
		}

		public Builder status(EMIStatus status) {
			this.status = status;
			return this;
		}

		public LoanEMISchedule build() {
			return new LoanEMISchedule(this);
		}

	}

	public UUID getLoanId() {
		return loanId;
	}

	public int getEmiNumber() {
		return emiNumber;
	}

	public long getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(long emiAmount) {
		this.emiAmount = emiAmount;
	}

	public long getAddOnLumpsum() {
		return addOnLumpsum;
	}

	public void setAddOnLumpsum(long addOnLumpsum) {
		this.addOnLumpsum = addOnLumpsum;
	}

	public EMIStatus getStatus() {
		return status;
	}

	public void setStatus(EMIStatus status) {
		this.status = status;
	}

	public boolean isEmiActive(){
		return status.isActive();
	}

}
