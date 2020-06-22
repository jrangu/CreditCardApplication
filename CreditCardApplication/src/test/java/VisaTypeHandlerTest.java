import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class VisaTypeHandlerTest {

    @Test
    void identifyTypePositive() {
        VisaTypeHandler handler = new VisaTypeHandler();
        assertEquals(handler.identifyType("4120000000000"), CreditCardType.CREDIT_CARD_TYPE_VISA);
    }

    @Test
    void identifyTypeNegative(){
        VisaTypeHandler handler = new VisaTypeHandler();
        assertEquals(handler.identifyType("4120000sd00000"), CreditCardType.CREDIT_CARD_TYPE_UNKNOWN);
        assertEquals(handler.identifyType("512000000000"), CreditCardType.CREDIT_CARD_TYPE_UNKNOWN);
    }
}