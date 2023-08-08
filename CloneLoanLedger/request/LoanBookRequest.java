package CloneLoanLedger.request;

import CloneLoanLedger.enums.RequestType;

public class LoanBookRequest extends AbstractRequest{
    private long loanAmount;
    private int tenureInYears;
    private double rateOfInterest;

    public LoanBookRequest(Builder b){
        super(b);
        this.loanAmount=b.loanAmount;
        this.tenureInYears=b.tenureInYears;
        this.rateOfInterest=b.rateOfInterest;
    }

    public static class Builder extends AbstractRequest.Builder<LoanBookRequest,Builder>{
        private long loanAmount;
        private int tenureInYears;
        private double rateOfInterest;

        public Builder(){
            super(RequestType.LOAN);
        }
        @Override
        public Builder self(){
            return this;
        }
        @Override
        public LoanBookRequest build(){
            return new LoanBookRequest(this);
        }

        public Builder loanAmount(long loanAmount){
            this.loanAmount=loanAmount;
            return self();
        }

        public Builder tenureInYears(int years){
            this.tenureInYears=years;
            return self();
        }
        public Builder rateOfInterest(double interest){
            this.rateOfInterest=interest;
            return self();
        }
    }
}
