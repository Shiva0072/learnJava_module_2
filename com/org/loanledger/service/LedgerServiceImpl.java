package com.org.loanledger.service;

import java.util.*;

import com.org.loanledger.dao.DaoManager;
import com.org.loanledger.entity.Loan;
import com.org.loanledger.entity.LoanEMISchedule;
import com.org.loanledger.enums.EMIStatus;
import com.org.loanledger.enums.LoanStatus;
import com.org.loanledger.exceptions.DuplicateLoanBookException;
import com.org.loanledger.exceptions.InvalidPaymentAmountException;
import com.org.loanledger.exceptions.LoanNotExistException;
import com.org.loanledger.request.Request;
import com.org.loanledger.util.LedgerUtility;

public class LedgerServiceImpl implements LedgerService {

	@Override
	public boolean bookLoan(Request request) throws Exception {
		UUID uid = UUID.randomUUID();
		Loan loan = new Loan.Builder().loanId(uid).bankName(request.getBankName())
				.customerName(request.getCustomerName()).loanAmount(request.getLoanAmount())
				.loanTenureInYears(request.getTenureInyears()).rateOfInterest(request.getRateOfInterest())
				.loanStatus(LoanStatus.ACTIVE).build();
		if (DaoManager.getLedgerDao().getLoanByUniqueKey(loan.getKey()) != null) {
			throw new DuplicateLoanBookException(String.format("loan already exists with bank : %s , customer name %s ",
					request.getBankName(), request.getCustomerName()));
		}
		int emiCount = loan.getLoanTenureInYears() * 12;
		long repaymentAmount = LedgerUtility.getRepaymentAmount(loan.getLoanAmount(), loan.getRateOfInterest(),
				loan.getLoanTenureInYears());
		long emiAmount = LedgerUtility.getEmiAmount(repaymentAmount, emiCount);
		loan.setRepaymentAmount(repaymentAmount);
		loan.setEmiAmount(emiAmount);
		List<LoanEMISchedule> emiSchedules = buildEmiScheduleForLoan(loan);
		DaoManager.getLedgerDao().insertLoan(loan, emiSchedules);
		return true;
	}

	private List<LoanEMISchedule> buildEmiScheduleForLoan(Loan loan) {
		int emiCount = loan.getLoanTenureInYears() * 12;
		long emiAmount = loan.getEmiAmount();
		List<LoanEMISchedule> emiSchedules = new ArrayList<>();
		long amountRemaining = loan.getRepaymentAmount();
		for (int i = 0; i <= emiCount; i++) {
			if (i == 0) {
				LoanEMISchedule emiSchedule = new LoanEMISchedule.Builder().emiNumber(i).emiAmount(0)
						.loanId(loan.getLoanId()).status(EMIStatus.ACTIVE).build();
				emiSchedules.add(emiSchedule);
			} else {
				if (amountRemaining < emiAmount) {
					emiAmount = amountRemaining;
				}
				LoanEMISchedule emiSchedule = new LoanEMISchedule.Builder().emiNumber(i).emiAmount(emiAmount)
						.loanId(loan.getLoanId()).status(EMIStatus.ACTIVE).build();
				amountRemaining -= emiAmount;
				emiSchedules.add(emiSchedule);
			}

		}

		return emiSchedules;
	}

	@Override
	public boolean makePayment(Request request) throws Exception {
		String loanKey = request.getBankName() + "-" + request.getCustomerName();
		if (DaoManager.getLedgerDao().getLoanByUniqueKey(loanKey) == null) {
			throw new LoanNotExistException(String.format("loan doesn't exists with bank : %s , customer name %s ",
					request.getBankName(), request.getCustomerName()));
		}

		Loan loan = DaoManager.getLedgerDao().getLoanByUniqueKey(loanKey);

		List<LoanEMISchedule> emiScheduels = DaoManager.getLedgerDao().getEmiScheduleForLoan(loan.getLoanId());
		emiScheduels.sort(Comparator.comparingInt(LoanEMISchedule::getEmiNumber));
		long amountPaid = getBalanceAmount(request.getEmiNumber(), emiScheduels);
		long remainingAmount = loan.getRepaymentAmount() - amountPaid;
		if (remainingAmount < request.getLumpsumAmount()) {
			throw new InvalidPaymentAmountException(
					String.format("payment amount is not valid. remaining amount: %s received amount %s",
							remainingAmount, request.getLumpsumAmount()));
		} else {
			LoanEMISchedule emi = emiScheduels.get(request.getEmiNumber());
			emi.setAddOnLumpsum(emi.getAddOnLumpsum() + request.getLumpsumAmount());
			remainingAmount -= request.getLumpsumAmount();

			int emiRemaining = LedgerUtility.getEMICountForAmount(remainingAmount, loan.getEmiAmount());
			long emiAmount = loan.getEmiAmount();
			int nextEmiNo = request.getEmiNumber() + 1;
			while (nextEmiNo <= request.getEmiNumber() + emiRemaining) {
				emi = emiScheduels.get(nextEmiNo);
				if (remainingAmount < emiAmount) {
					emiAmount = remainingAmount;
				}
				if (emiAmount <= 0) {
					break;
				}
				emi.setEmiAmount(emiAmount);
				remainingAmount -= emiAmount;
				nextEmiNo++;
			}
			while (nextEmiNo < emiScheduels.size()) {
				emi = emiScheduels.get(nextEmiNo);
				emi.setStatus(EMIStatus.PRE_CLOSURE);
				nextEmiNo++;
			}
		}
		DaoManager.getLedgerDao().updateEmiSchedule(loan.getLoanId(), emiScheduels);
		return true;

	}

	@Override
	public String checkBalance(Request request) throws Exception {
		String loanKey = request.getBankName() + "-" + request.getCustomerName();
		if (DaoManager.getLedgerDao().getLoanByUniqueKey(loanKey) == null) {
			throw new LoanNotExistException(String.format("loan doesn't exists with bank : %s , customer name %s ",
					request.getBankName(), request.getCustomerName()));
		}
		Loan loan = DaoManager.getLedgerDao().getLoanByUniqueKey(loanKey);
		List<LoanEMISchedule> emiScheduels = DaoManager.getLedgerDao().getEmiScheduleForLoan(loan.getLoanId());
		Collections.sort(emiScheduels, (a, b) -> a.getEmiNumber() - b.getEmiNumber());
		long amountPaid = getBalanceAmount(request.getEmiNumber(), emiScheduels);
		int emiRemaining = LedgerUtility.getEMICountForAmount(loan.getRepaymentAmount() - amountPaid,
				loan.getEmiAmount());
		StringJoiner joiner = new StringJoiner(" ");
		joiner.add(request.getBankName()).add(request.getCustomerName()).add(String.valueOf(amountPaid))
				.add(String.valueOf(emiRemaining));
		return joiner.toString();
	}

	private long getBalanceAmount(long emiNo, List<LoanEMISchedule> emiScheduels) {
		long amountPaid = 0;
		int i = 0;
		while (i <= emiNo) {
			if (i >= emiScheduels.size()) {
				break;
			}
			LoanEMISchedule emi = emiScheduels.get(i);
			if (emi.getStatus() == EMIStatus.ACTIVE) {
				amountPaid += emi.getEmiAmount() + emi.getAddOnLumpsum();
			}
			i++;
		}
		return amountPaid;
	}

}
