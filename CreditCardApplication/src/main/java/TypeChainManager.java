public class TypeChainManager {

    private static TypeHandler typeHandler;

    static {
        typeHandler = new VisaTypeHandler();
        TypeHandler masterTypeHandler = new MasterTypeHandler();
        TypeHandler amExTypeHandler = new AmExTypeHandler();
        TypeHandler discoverTypeHandler = new DiscoverTypeHandler();
        typeHandler.setSuccessor(masterTypeHandler);
        masterTypeHandler.setSuccessor(amExTypeHandler);
        amExTypeHandler.setSuccessor(discoverTypeHandler);
    }

    public static CreditCardType getType(String number) {
        return typeHandler.identifyType(number);
    }
}
