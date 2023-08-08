package com.org.loanledger.service;

public interface ServiceManager {
	public static LedgerService getLedgerService() {
		return new LedgerServiceImpl();
	}
}
