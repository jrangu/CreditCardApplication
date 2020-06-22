import java.util.Optional;

public class AmExTypeHandler implements TypeHandler {
    private Optional<TypeHandler> successor = Optional.empty();

    public AmExTypeHandler() {
    }

    public AmExTypeHandler(TypeHandler successor) {
        assert successor != null;
        this.successor = Optional.of(successor);
    }

    public CreditCardType identifyType(String cardNumber) {
        // First digit is a 3 and second digit a 4 or 7. Length is 15 digits.
        if (cardNumber != null && cardNumber.length() == 15) {
            if (cardNumber.startsWith("3")) {
                int secondDigit = Integer.parseInt(cardNumber.substring(1, 2));
                if (secondDigit == 4 || secondDigit == 7) {
                    return CreditCardType.CREDIT_CARD_TYPE_AMEX;
                }
            }
        }
        if (successor.isPresent()) {
            return successor.get().identifyType(cardNumber);
        }
        return CreditCardType.CREDIT_CARD_TYPE_UNKNOWN;
    }

    public void setSuccessor(TypeHandler next) {
        this.successor = Optional.of(next);
    }
}
