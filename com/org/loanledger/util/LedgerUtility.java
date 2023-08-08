package com.org.loanledger.util;

public class LedgerUtility {

	public static long getInterestPayable(long p, double r, int t) {
		return Math.round((p * r * t) / 100);
	}

	public static long getRepaymentAmount(long p, double r, int t) {
		long interest = getInterestPayable(p, r, t);
		return p + interest;
	}

	public static long getEmiAmount(long repaymentAmount, int tenureInMonths) {
		return (long) Math.ceil((double) repaymentAmount / tenureInMonths);
	}

	public static int getEMICountForAmount(long amount, long emiAmount) {
		return (int) Math.ceil((double) amount / emiAmount);
	}

}
