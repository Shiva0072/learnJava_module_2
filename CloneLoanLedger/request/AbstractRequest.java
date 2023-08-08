package CloneLoanLedger.request;

import CloneLoanLedger.enums.RequestType;

public abstract class AbstractRequest implements Request{
    protected RequestType requestType;
    protected String bankName;
    protected String customerName;

    public AbstractRequest(Builder<?,?> b){
        this.requestType=b.requestType;
        this.bankName=b.bankName;
        this.customerName=b.customerName;
    }

    public static abstract class Builder<E extends AbstractRequest,T extends Builder<E,T>>{
        private RequestType requestType;
        private String bankName;
        private String customerName;

        public abstract T self();
        public abstract E build();

        public Builder(RequestType requestType){
            this.requestType=requestType;
        }

        public T bankName(String bankName){
            this.bankName=bankName;
            return self();
        }

        public T customerName(String customerName){
            this.customerName=customerName;
            return self();
        }
    }

    @Override
    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public String getBankName() {
        return bankName;
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }
}
