package ECommerce.payment;

public interface ReceiptGenerator {
    String generate(String orderId, double amountCharged);
}
