package CloneLoanLedger.factory;

import CloneLoanLedger.request.AbstractRequest;
import CloneLoanLedger.request.LoanBookRequest;
import CloneLoanLedger.request.LoanCheckBalanceRequest;
import CloneLoanLedger.request.LumpsumPaymentRequest;

public class RequestFactory {
    public static AbstractRequest getRequest(String requestString){
        String[] args = requestString.split(" ");

        switch (args[0]){
            case "LOAN" : return new LoanBookRequest.Builder().
                    bankName(args[1]).
                    customerName(args[2]).
                    loanAmount(Long.valueOf(args[3])).
                    tenureInYears(Integer.valueOf(args[4])).
                    rateOfInterest(Double.valueOf(args[5])).
                    build();

            case "PAYMENT" : return new LumpsumPaymentRequest.Builder().
                    bankName(args[1]).
                    customerName(args[2]).
                    lumpsumAmount(Long.valueOf(args[3])).
                    emiNumber(Integer.valueOf(args[4])).
                    build();

            case "BALANCE" : return new LoanCheckBalanceRequest.Builder().
                    bankName(args[1]).
                    customerName(args[2]).
                    emiNumber(Integer.valueOf(args[3])).
                    build();

            default: throw new IllegalArgumentException("Unexpected request arguments : ");
        }

    }

}
