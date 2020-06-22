import java.util.Optional;

public class DiscoverTypeHandler implements TypeHandler {
    private Optional<TypeHandler> successor = Optional.empty();

    public DiscoverTypeHandler() {
    }

    public DiscoverTypeHandler(TypeHandler successor) {
        assert successor != null;
        this.successor = Optional.of(successor);
    }

    public CreditCardType identifyType(String cardNumber) {
        // Discover card logic goes here
        if (cardNumber != null && cardNumber.length() == 16) {
            if (cardNumber.substring(0, 4).equals("6011")) {
                return CreditCardType.CREDIT_CARD_TYPE_DISCOVER;
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
