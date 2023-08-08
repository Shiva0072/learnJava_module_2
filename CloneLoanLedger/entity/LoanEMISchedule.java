package CloneLoanLedger.entity;

import CloneLoanLedger.enums.EMIStatus;

import java.util.UUID;

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

    public static class Builder{
        private UUID loanId;
        private int emiNumber;
        private long emiAmount;
        private long addOnLumpsum;
        private EMIStatus status;

        public Builder(){};

        public Builder loanId(UUID id){
            this.loanId=id;
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

        public LoanEMISchedule build(){
            return new LoanEMISchedule(this);
        }
    }


}
