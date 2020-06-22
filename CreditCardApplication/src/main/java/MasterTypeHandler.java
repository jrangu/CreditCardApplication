import java.util.Optional;

public class MasterTypeHandler implements TypeHandler {
    private Optional<TypeHandler> successor = Optional.empty();

    public MasterTypeHandler() {
    }

    public MasterTypeHandler(TypeHandler successor) {
        assert successor != null;
        this.successor = Optional.of(successor);
    }

    public CreditCardType identifyType(String cardNumber) {
        // Master card logic goes here
        if (cardNumber != null && cardNumber.length() == 16) {
            if (cardNumber.startsWith("5")) {
                int secondDigit = Integer.parseInt(cardNumber.substring(1, 2));
                if (secondDigit > 0 && secondDigit < 6) {
                    return CreditCardType.CREDIT_CARD_TYPE_MASTER_CARD;
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
