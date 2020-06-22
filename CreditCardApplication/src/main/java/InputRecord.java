import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class InputRecord {
    private boolean isValid;
    private Optional<String> cardNumber;
    private Optional<String> name;
    private Optional<Date> expiry;

    public InputRecord(String cardNumber, String expiry, String name) {
        this.cardNumber = validateCardNumber(cardNumber);
        this.expiry = validateExpiry(expiry);
        this.name = Optional.ofNullable(name);
    }

    private Optional<Date> validateExpiry(String expiry) {
        if (expiry == null) {
            return Optional.empty();
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm/dd/yyyy");
            return Optional.of(simpleDateFormat.parse(expiry));
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

    private Optional<String> validateCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.equals("") || cardNumber.length() > 16) {
            this.isValid = false;
        } else {
            try {
                String number = String.valueOf(Double.valueOf(cardNumber).longValue());
                this.isValid = true;
                return Optional.of(number);
            } catch (NumberFormatException e) {
                this.isValid = false;
            }
        }
        return Optional.ofNullable(cardNumber);
    }

    public Optional<String> getCardNumber() {
        return cardNumber;
    }

    public Optional<String> getName() {
        return name;
    }

    public Optional<Date> getExpiry() {
        return expiry;
    }

    public boolean isValid() {
        return isValid;
    }
}
