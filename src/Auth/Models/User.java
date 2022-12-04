package Auth.Models;

import Services.LandlineServices.LandlineMonthly;

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

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void userPanel() {
        System.out.println(String.format("Welcome Back %s!", this.getName()));
        System.out.println(String.format("your ballance  %f!", this.getBalance()));
        LandlineMonthly landlineMonthly = new LandlineMonthly();
        landlineMonthly.pay();

        // variable in string java
        // System.out.println("Your balance is ${balance}!");
    }
}
