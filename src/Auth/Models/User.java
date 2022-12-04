package Auth.Models;

import java.util.Scanner;
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

    public void decreaseBalance(float amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
        }
    }

    public void userPanel() {
        System.out.println(String.format("Welcome Back %s!", this.getName()));
        System.out.println(String.format("your ballance  %f!", this.getBalance()));
        UserOptionsMenu();

        // variable in string java
        // System.out.println("Your balance is ${balance}!");
    }

    public static void UserOptionsMenu(){
        int option;
        String Search;
        float amount;
        System.out.println("1. Recharge Wallet Balance\n2. Show all services\n3. Search Services\n4.logout");
        Scanner sc = new Scanner(System.in);
        option = sc.nextInt();
        if (option == 1) {
            System.out.println("Enter the amount you want to recharge");
            amount = sc.nextFloat();
            //connect payment class
        }
        else if (option == 2){
            MainMenuView.showServices();
        }
        else if (option == 3){
            System.out.println("Enter Service name:");
            Search = sc.nextLine();
        }
        else if (option == 4){
            System.out.println("loging out");
            MainMenuView.displayAuthMenu();
        } 
        else{
            System.out.println("Invalid option");
            UserOptionsMenu();
        }

    }
}
