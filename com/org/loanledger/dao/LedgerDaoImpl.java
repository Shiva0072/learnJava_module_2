package com.org.loanledger.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.org.loanledger.entity.Loan;
import com.org.loanledger.entity.LoanEMISchedule;

public class LedgerDaoImpl implements LedgerDao {
	private Map<String, Loan> loansByKey;
	private Map<UUID, List<LoanEMISchedule>> emiSchedulesByLoanId;

	public LedgerDaoImpl() {
		this.loansByKey = new HashMap<>();
		this.emiSchedulesByLoanId = new HashMap<>();
	}

	public static final LedgerDao INSTANCE = new LedgerDaoImpl();

	@Override
	public void insertLoan(Loan loan, List<LoanEMISchedule> emiSchedules) {
		loansByKey.put(loan.getKey(), loan);
		emiSchedulesByLoanId.put(loan.getLoanId(), emiSchedules);
	}

	@Override
	public void updateEmiSchedule(UUID loanId, List<LoanEMISchedule> emiSchedules) {
		emiSchedulesByLoanId.put(loanId, emiSchedules);
	}

	@Override
	public Loan getLoanByUniqueKey(String loanKey) {
		return loansByKey.get(loanKey);
	}

	@Override
	public List<LoanEMISchedule> getEmiScheduleForLoan(UUID loanId) {
		return emiSchedulesByLoanId.get(loanId);
	}

}
