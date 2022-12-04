package Payment;

import java.util.Scanner;

public class CreditCardPayment implements Payment {
    String cardNumber;
    String cardHolderName;
    String expirationDate;
    String cvv;

    private void getCreditCardInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your card number: ");
        this.cardNumber = scanner.nextLine();
        System.out.println("Enter your card holder name: ");
        this.cardHolderName = scanner.nextLine();
        System.out.println("Enter your card expiration date: ");
        this.expirationDate = scanner.nextLine();
        System.out.println("Enter your card cvv: ");
        this.cvv = scanner.nextLine();
    }

    private float getBalance() {
        // connect to bank api and get balance
        return (float) (Math.random() * 1000);
    }

    private void DecreaseBalance(float amount) {
        // connect to bank api and decrease balance
        System.out.println("Decreased balance from Credit Card by " + amount);
    }

    @Override
    public Boolean checkBalanceAndPay(float amount) {
        getCreditCardInfo();
        if (getBalance() >= amount) {
            DecreaseBalance(amount);
            return true;
        } else {
            return false;
        }
    }

}
