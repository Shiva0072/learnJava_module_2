package CloneLoanLedger.enums;

public enum EMIStatus implements Status {
    ACTIVE{
        @Override
        public boolean isActive() {
            return true;
        }
    }, PRE_CLOSURE{
        @Override
        public boolean isActive() {
            return false;
        }
    };
}
