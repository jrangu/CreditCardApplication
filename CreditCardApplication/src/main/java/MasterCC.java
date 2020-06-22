import java.util.Date;

public class MasterCC implements CreditCard {
    private final CreditCardType type = CreditCardType.CREDIT_CARD_TYPE_MASTER_CARD;
    private String number;
    private Date expiry;
    private String name;

    public MasterCC(String number, Date expiry, String name) {
        this.number = number;
        this.expiry = expiry;
        this.name = name;
    }

    @Override
    public CreditCardType getType() {
        return type;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public Date getExpiry() {
        return expiry;
    }

    @Override
    public String getName() {
        return name;
    }
}
