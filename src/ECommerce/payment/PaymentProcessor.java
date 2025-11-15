package ECommerce.payment;

public interface PaymentProcessor {
    boolean process(double amount);
}
