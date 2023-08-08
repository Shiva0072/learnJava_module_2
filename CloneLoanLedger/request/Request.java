package CloneLoanLedger.request;

import CloneLoanLedger.enums.RequestType;

public interface Request {
    RequestType getRequestType();
    String getBankName();
    String getCustomerName();


}
