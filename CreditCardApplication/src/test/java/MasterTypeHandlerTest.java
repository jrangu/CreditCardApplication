import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

//@RunWith(JUnit4.class)
public class MasterTypeHandlerTest {

    @Test
    void identifyTypePositive() {
        MasterTypeHandler masterTypeHandler = new MasterTypeHandler();
        assertEquals(masterTypeHandler.identifyType("5110000000000000"), CreditCardType.CREDIT_CARD_TYPE_MASTER_CARD);
    }

    @Test
    void identifyTypeNegative(){
        MasterTypeHandler masterTypeHandler = new MasterTypeHandler();
        assertEquals(masterTypeHandler.identifyType("511000000000000013"), CreditCardType.CREDIT_CARD_TYPE_UNKNOWN);
        assertEquals(masterTypeHandler.identifyType("6011000000000022"), CreditCardType.CREDIT_CARD_TYPE_UNKNOWN);

    }
}