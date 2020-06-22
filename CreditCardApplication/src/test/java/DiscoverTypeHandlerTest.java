import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class DiscoverTypeHandlerTest {

    @Test
    void identifyTypePositive() {
        DiscoverTypeHandler handler = new DiscoverTypeHandler();
        assertEquals(handler.identifyType("6011000000000022"), CreditCardType.CREDIT_CARD_TYPE_DISCOVER);
    }

    @Test
    void identifyTypeNegative(){
        DiscoverTypeHandler handler = new DiscoverTypeHandler();
        assertEquals(handler.identifyType("6010000000000022"), CreditCardType.CREDIT_CARD_TYPE_UNKNOWN);
        assertEquals(handler.identifyType("601000000000002223"),CreditCardType.CREDIT_CARD_TYPE_UNKNOWN);
    }
}