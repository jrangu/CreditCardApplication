import java.util.Date;

public interface CreditCard {
    CreditCardType getType();

    String getNumber();

    Date getExpiry();

    String getName();
}