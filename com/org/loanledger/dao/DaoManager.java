package com.org.loanledger.dao;

public interface DaoManager {

	public static LedgerDao getLedgerDao() {
		return LedgerDaoImpl.INSTANCE;
	}

}
