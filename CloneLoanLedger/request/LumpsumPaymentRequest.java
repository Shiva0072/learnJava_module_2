package CloneLoanLedger.request;

import CloneLoanLedger.enums.RequestType;

public class LumpsumPaymentRequest extends AbstractRequest{

    private long lumpsumAmount;
    private int emiNumber;

    public LumpsumPaymentRequest(Builder b){
        super(b);
        lumpsumAmount=b.lumpsumAmount;
        emiNumber=b.emiNumber;
    }

    public static class Builder extends AbstractRequest.Builder<LumpsumPaymentRequest,Builder>{
        private long lumpsumAmount;
        private int emiNumber;

        public Builder(){
            super(RequestType.LUMPSUM_PAYMENT);
        }

        @Override
        public Builder self(){
            return this;
        }
        @Override
        public LumpsumPaymentRequest build(){
            return new LumpsumPaymentRequest(this);
        }

        public Builder lumpsumAmount(long lsAmt){
            this.lumpsumAmount=lsAmt;
            return self();
        }

        public Builder emiNumber(int emiNum){
            this.emiNumber=emiNum;
            return self();
        }
    }


}
