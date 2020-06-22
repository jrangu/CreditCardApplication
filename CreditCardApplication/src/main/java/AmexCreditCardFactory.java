public class AmexCreditCardFactory implements CreditCardFactory {
    @Override
    public CreditCard getCreditCard(InputRecord record) {
        return new AmexCC(record.getCardNumber().get(), record.getExpiry().isPresent() ? record.getExpiry().get() : null, record.getName().isPresent() ? record.getName().get() : null);
    }
}
