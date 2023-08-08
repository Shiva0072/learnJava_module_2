package com.org.loanledger.dao;

import java.util.List;
import java.util.UUID;

import com.org.loanledger.entity.Loan;
import com.org.loanledger.entity.LoanEMISchedule;

public interface LedgerDao {

	void insertLoan(Loan loan, List<LoanEMISchedule> emiSchedule);

	void updateEmiSchedule(UUID loanId, List<LoanEMISchedule> emiSchedule);

	Loan getLoanByUniqueKey(String loanKey);

	List<LoanEMISchedule> getEmiScheduleForLoan(UUID loanId);

}
