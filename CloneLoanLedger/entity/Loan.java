package CloneLoanLedger.entity;

import CloneLoanLedger.enums.LoanStatus;

import java.util.UUID;

public class Loan implements Entity{
    private UUID loanID;
    private String bankName;
    private String customerName;
    private long loanAmount;
    private long repaymentAmount;
    private int loanTenureInYears;
    private double rateOfInterest;
    private long emiAmount;
    private LoanStatus loanStatus;

    private Loan(Builder b) {
        this.loanID = b.loanID;
        this.bankName = b.bankName;
        this.customerName = b.customerName;
        this.loanAmount = b.loanAmount;
        this.repaymentAmount = b.repaymentAmount;
        this.loanTenureInYears = b.loanTenureInYears;
        this.rateOfInterest = b.rateOfInterest;
        this.emiAmount = b.emiAmount;
        this.loanStatus = b.loanStatus;
    }

    public static class Builder{

        private UUID loanID;
        private String bankName;
        private String customerName;
        private long loanAmount;
        private long repaymentAmount;
        private int loanTenureInYears;
        private double rateOfInterest;
        private long emiAmount;
        private LoanStatus loanStatus;

        public Builder(){}

        public Builder loanId(UUID loanID){
            this.loanID=loanID;
            return this;
        }

        public Builder bankName(String bankName){
            this.bankName=bankName;
            return this;
        }

        public Builder customerName(String customerName){
            this.customerName=customerName;
            return this;
        }

        public Builder loanAmount(long loanAmount){
            this.loanAmount=loanAmount;
            return this;
        }

        public Builder repaymentAmount(long repaymentAmount){
            this.repaymentAmount=repaymentAmount;
            return this;
        }

        public Builder loanTenureInYears(int years){
            this.loanTenureInYears=years;
            return this;
        }

        public Builder rateOfInterest(double rateOfInterest) {
            this.rateOfInterest = rateOfInterest;
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

        public Loan build(){
            return new Loan(this);
        }


    }

}
