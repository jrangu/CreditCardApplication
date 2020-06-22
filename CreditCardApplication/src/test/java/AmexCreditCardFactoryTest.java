import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class AmexCreditCardFactoryTest {
    @Test
    void getCreditCard() throws ParseException {
        CreditCardFactory creditCardFactory = new AmexCreditCardFactory();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm/dd/yyyy");
        String cardNumber = "124";
        String date = "03/03/2021";
        String user = "test user";
        CreditCard card = creditCardFactory.getCreditCard(new InputRecord(cardNumber, date, user));
        Assert.assertEquals(card.getNumber(), cardNumber);
        Assert.assertEquals(card.getExpiry(), simpleDateFormat.parse(date));
        Assert.assertEquals(card.getName(), user);
        Assert.assertEquals(card.getType(), CreditCardType.CREDIT_CARD_TYPE_AMEX);
    }
}