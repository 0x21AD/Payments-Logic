package Auth.Models;

import java.util.Scanner;

import Payment.CreditCardPayment;
import UI.MainMenuView;

public class User extends AbstractUser {
    private float balance;
    // singelton pattern
    private static User user = null;

    private User(String name, String email, String password, float balance) {
        super(name, email, password);
        this.balance = balance;
    }

    public static User getInstance(String name, String email, String password, float balance) {
        if (user == null) {
            user = new User(name, email, password, balance);
        }
        return user;
    }

    public static User getInstance() {
        if (user == null) {
            user = new User("", "", "", 0);
        }
        return user;
    }

    public float getBalance() {
        return balance;
    }

    public void addBalance(float amount) {
        this.balance += amount;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void userPanel() {
        System.out.println(String.format("Welcome Back %s!", this.getName()));
        System.out.println(String.format("your ballance  %f!", this.getBalance()));
        UserOptionsMenu();
    }

    public static void UserOptionsMenu() {
        int option;
        float amount;
        System.out.println("1. Recharge Wallet Balance\n2. Show services\n3. logout");
        Scanner sc = new Scanner(System.in);
        option = sc.nextInt();
        if (option == 1) {
            System.out.println("Enter the amount you want to recharge");
            amount = sc.nextFloat();
            CreditCardPayment creditCardPayment = new CreditCardPayment();
            if (creditCardPayment.checkBalanceAndPay(amount)) {
                User.getInstance().addBalance(amount);
                System.out.println("Recharge " + amount + "$ successfully");
                System.out.println("Your new balance is " + User.getInstance().getBalance() + "$");
            } else {
                System.out.println("Recharge failed");
                System.out.println("insufficient balance");
            }
            UserOptionsMenu();
        } else if (option == 2) {
            MainMenuView.showServices();
        } else if (option == 3) {
            System.out.println("loging out");
            MainMenuView.displayAuthMenu();
        } else {
            System.out.println("Invalid option");
            UserOptionsMenu();
        }

    }
}
