import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmExTypeHandlerTest {

    @Test
    void identifyTypePositive() {
        AmExTypeHandler handler = new AmExTypeHandler();
        assertEquals(handler.identifyType("371000000000000"), CreditCardType.CREDIT_CARD_TYPE_AMEX);
        assertEquals(handler.identifyType("341000000000000"), CreditCardType.CREDIT_CARD_TYPE_AMEX);
    }

    @Test
    void identifyNegative() {
        AmExTypeHandler handler = new AmExTypeHandler();
        assertEquals(handler.identifyType("391000000000000"), CreditCardType.CREDIT_CARD_TYPE_UNKNOWN);
        assertEquals(handler.identifyType("34100000000000012"), CreditCardType.CREDIT_CARD_TYPE_UNKNOWN);
        assertEquals(handler.identifyType("37100000000000012678"), CreditCardType.CREDIT_CARD_TYPE_UNKNOWN);
    }
}