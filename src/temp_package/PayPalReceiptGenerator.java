package temp_package;

public class PayPalReceiptGenerator implements ReceiptGenerator {
    @Override
    public String generate(String orderId, double amountCharged) {
        return "Receipt#" + orderId + " - Paid via PayPal: $" + String.format("%.2f", amountCharged);
    }
}