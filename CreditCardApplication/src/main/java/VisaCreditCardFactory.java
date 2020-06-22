public class VisaCreditCardFactory implements CreditCardFactory {
    @Override
    public CreditCard getCreditCard(InputRecord record) {
        return new VisaCC(record.getCardNumber().get(), record.getExpiry().isPresent() ? record.getExpiry().get() : null, record.getName().isPresent() ? record.getName().get() : null);
    }
}
