public interface TypeHandler {
    CreditCardType identifyType(String cardNumber);

    void setSuccessor(TypeHandler next);
}
