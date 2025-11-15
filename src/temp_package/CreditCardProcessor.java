package temp_package;

public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public boolean process(double amount) {
        System.out.println("Processing credit card payment for $" + String.format("%.2f", amount));
        return true;
    }
}