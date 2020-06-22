public class OutputRecord {
    private String cardNumber;
    private String cardType;
    private String error;

    public OutputRecord(String cardNumber, String cardType, String error) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.error = error;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public String getError() {
        return error;
    }
}
