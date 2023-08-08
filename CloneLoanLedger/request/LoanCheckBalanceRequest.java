package CloneLoanLedger.request;

import CloneLoanLedger.enums.RequestType;

public class LoanCheckBalanceRequest extends AbstractRequest{

    private int emiNumber;

    public LoanCheckBalanceRequest(Builder b){
        super(b);
        this.emiNumber=b.emiNumber;
    }

    public static class Builder extends AbstractRequest.Builder<LoanCheckBalanceRequest,Builder>{
        private int emiNumber;

        public Builder(){
            super(RequestType.BALANCE);
        }
        @Override
        public Builder self(){
            return this;
        }

        @Override
        public LoanCheckBalanceRequest build(){
            return new LoanCheckBalanceRequest(this);
        }

        public Builder emiNumber(int emiNumber){
            this.emiNumber = emiNumber;
            return self();
        }
    }

}
