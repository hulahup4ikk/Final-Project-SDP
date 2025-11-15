package ECommerce.payment;

public class CreditCardReceiptGenerator implements ReceiptGenerator {
    @Override
    public String generate(String orderId, double amountCharged) {
        return "Receipt#" + orderId + " - Paid by credit card: $" + String.format("%.2f", amountCharged);
    }
}