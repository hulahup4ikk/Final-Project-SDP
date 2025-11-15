package ECommerce.payment;

public interface PaymentFactory {
    PaymentProcessor createProcessor();

    ReceiptGenerator createReceiptGenerator();
}
