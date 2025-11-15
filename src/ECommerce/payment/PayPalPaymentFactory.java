package ECommerce.payment;

public class PayPalPaymentFactory implements PaymentFactory {
    @Override
    public PaymentProcessor createProcessor() {
        return new PayPalProcessor();
    }

    @Override
    public ReceiptGenerator createReceiptGenerator() {
        return new PayPalReceiptGenerator();
    }
}
