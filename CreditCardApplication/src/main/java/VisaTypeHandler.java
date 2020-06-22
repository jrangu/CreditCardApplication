import java.util.Optional;

public class VisaTypeHandler implements TypeHandler {
    private Optional<TypeHandler> successor = Optional.empty();

    public VisaTypeHandler() {
    }

    public VisaTypeHandler(TypeHandler successor) {
        assert successor != null;
        this.successor = Optional.of(successor);
    }

    public CreditCardType identifyType(String cardNumber) {
        // Visa card logic goes here
        if (cardNumber != null && (cardNumber.length() == 13 || cardNumber.length() == 16)) {
            if (cardNumber.startsWith("4")) {
                return CreditCardType.CREDIT_CARD_TYPE_VISA;
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
