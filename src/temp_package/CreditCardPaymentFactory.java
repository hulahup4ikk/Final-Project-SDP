package temp_package;

public class CreditCardPaymentFactory implements PaymentFactory {
    @Override
    public PaymentProcessor createProcessor() {
        return new CreditCardProcessor();
    }

    @Override
    public ReceiptGenerator createReceiptGenerator() {
        return new CreditCardReceiptGenerator();
    }
}