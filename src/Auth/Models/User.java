package Auth.Models;

import java.util.ArrayList;
import java.util.Scanner;

import Auth.SqlLite;
import Payment.CreditCardPayment;
import Payment.Transaction;
import UI.MainMenuView;
import UI.DataStoreRuntime;

public class User extends AbstractUser {
    private float balance;
    private static User user = null;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public ArrayList<Transaction> getTransactions() {
        transactions.clear();
        for (Transaction transaction : DataStoreRuntime.getInstance().getTransactions()) {
            if (transaction.getUser().getEmail().equals(User.getInstance().getEmail())) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

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

    public void setBalance(float balance) {
        this.balance = balance;
        SqlLite.updateBalance(getEmail(), this.balance);
    }

    public void decreaseBalance(float amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            SqlLite.updateBalance(getEmail(), this.balance);
        }
    }

    public void userPanel() {
        System.out.println(String.format("Welcome Back %s!", this.getName()));
        System.out.println(String.format("your ballance  %f!", this.getBalance()));
        UserOptionsMenu();
    }

    public static void UserOptionsMenu() {
        int option;
        float amount;
        System.out.println("1. Recharge Wallet Balance\n2. Show services\n3. show Transactions\n4. logout \n");
        Scanner sc = new Scanner(System.in);
        try {
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
                int selectedService;

                ArrayList<Transaction> userTransactions = User.getInstance().getTransactions();
                User.getInstance().showTransactions(userTransactions);
                System.out.println("0. go back");
                System.out.print("Choose transaction to refund:");
                selectedService = sc.nextInt();
                if (selectedService == 0) {
                    UserOptionsMenu();
                }
                while (selectedService > userTransactions.size()) {
                    System.out.println("Invalid option");
                    User.getInstance().showTransactions(userTransactions);
                    System.out.println("0. go back");
                    System.out.print("Choose transaction to refund:");
                    selectedService = sc.nextInt();
                    if (selectedService == 0) {
                        UserOptionsMenu();
                    }
                }
                Transaction transaction = userTransactions.get(selectedService - 1);
                // if transaction doesn't exists in array the add
                if (!DataStoreRuntime.getInstance().getRefundServices().contains(transaction)) {
                    DataStoreRuntime.getInstance().addRefund(transaction);
                    System.out.println("Refund request sent successfully");
                } else {
                    System.out.println("Refund request already sent");
                }
                UserOptionsMenu();
            } else if (option == 4) {
                System.out.println("loging out");
                clearUser();
                MainMenuView.displayAuthMenu();
            } else {
                System.out.println("Invalid option");
                UserOptionsMenu();
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
            UserOptionsMenu();
        }

    }

    private void showTransactions(ArrayList<Transaction> userTransactions) {
        for (int i = 0; i < userTransactions.size(); i++) {
            System.out.println(i + 1 + ". " + userTransactions.get(i).getService().getServiceProviderName() + " "
                    + userTransactions.get(i).getAmount() + "$");
        }
    }
}
