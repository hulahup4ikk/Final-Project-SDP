package temp_package;

public interface PaymentFactory {
    PaymentProcessor createProcessor();

    ReceiptGenerator createReceiptGenerator();
}
