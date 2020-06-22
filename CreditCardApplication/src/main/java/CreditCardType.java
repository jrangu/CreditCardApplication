public enum CreditCardType {
    CREDIT_CARD_TYPE_MASTER_CARD("MasterCard"),
    CREDIT_CARD_TYPE_VISA("Visa"),
    CREDIT_CARD_TYPE_AMEX("AmericanExpress"),
    CREDIT_CARD_TYPE_DISCOVER("Discover"),
    CREDIT_CARD_TYPE_UNKNOWN("Invalid");

    private final String string;

    CreditCardType(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
