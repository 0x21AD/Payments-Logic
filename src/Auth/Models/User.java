package Auth.Models;

import java.util.ArrayList;
import Auth.SqlLite;
import Payment.CreditCardPayment;
import Payment.Transaction;
import RuntimeData.DataStoreRuntime;
import UI.MainMenuView;
import UI.InputValidator;

public class User extends AbstractUser {
    private float balance;
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

    public static void clearUser() {
        user = null;
    }

    public float getBalance() {
        return balance;
    }

    public void addBalance(float amount) {
        this.balance += amount;
        SqlLite.updateBalance(getEmail(), this.balance);
    }

    public void decreaseBalance(float amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            SqlLite.updateBalance(getEmail(), this.balance);
        }
    }

    public void printBalance() {
        System.out.println(String.format("your ballance is  %f$", this.getBalance()));
    }

    public ArrayList<Transaction> getTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        for (Transaction transaction : DataStoreRuntime.getInstance().getTransactions()) {
            if (transaction.getUser().getEmail().equals(User.getInstance().getEmail())) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    public void userPanel() {
        System.out.println(String.format("Welcome Back %s!", this.getName()));
        printBalance();
        userOptionsMenu();
    }

    public static void userOptionsMenu() {
        DataStoreRuntime dts = DataStoreRuntime.getInstance();
        System.out.println("User Panel");
        try {
            int option = InputValidator.validateInputUserMenu();
            if (option == 1) {
                float amount = InputValidator.validateInputAmountRecharge();
                CreditCardPayment creditCardPayment = new CreditCardPayment();
                if (creditCardPayment.checkBalanceAndPay(amount)) {
                    User.getInstance().addBalance(amount);
                    System.out.println("Recharge " + amount + "$ successfully");
                    User.getInstance().printBalance();
                } else {
                    System.out.println("Recharge failed");
                    System.out.println("insufficient balance");
                }
            } else if (option == 2) {
                User.getInstance().printBalance();
            } else if (option == 3) {
                MainMenuView.showServices();
                return;
            } else if (option == 4) {
                ArrayList<Transaction> userTransactions = User.getInstance().getTransactions();
                int selectedService = InputValidator.validateInputTransactions(userTransactions);
                if (selectedService == userTransactions.size() + 1) {
                    userOptionsMenu();
                }
                Transaction transaction = userTransactions.get(selectedService - 1);
                // if transaction doesn't exists in array the add
                if (!dts.getRefundServices().contains(transaction)) {
                    dts.addRefund(transaction);
                    System.out.println("Refund request sent successfully");
                } else {
                    System.out.println("Refund request already sent");
                }
            } else if (option == 5) {
                System.out.println("Logging out...");
                clearUser();
                MainMenuView.displayAuthMenu();
                return;
            }
            userOptionsMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Invalid input");
            userOptionsMenu();
        }
    }

}
