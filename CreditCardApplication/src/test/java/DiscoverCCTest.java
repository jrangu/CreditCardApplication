import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class DiscoverCCTest {

    private CreditCard creditCard;
    private String number;
    private Date expiry;
    private String name;

    @BeforeEach
    public void setup() throws ParseException {
        number = "123";
        expiry = new SimpleDateFormat("mm/dd/yyyy").parse("03/03/2021");
        name = "test user";
        creditCard = new DiscoverCC(number, expiry, name);
    }

    @Test
    void getType() {
        Assert.assertEquals(creditCard.getType(), CreditCardType.CREDIT_CARD_TYPE_DISCOVER);
    }

    @Test
    void getNumber() {
        Assert.assertEquals(creditCard.getNumber(), number);
    }

    @Test
    void getExpiry() {
        Assert.assertEquals(creditCard.getExpiry(), expiry);
    }

    @Test
    void getName() {
        Assert.assertEquals(creditCard.getName(), name);
    }
}