package Auth.Models;

public class User extends AbstractUser {
    private float balance;

    public User(String name, String email, String password, float balance) {
        super(name, email, password);
        this.balance = balance;
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
        // variable in string java
        // System.out.println("Your balance is ${balance}!");
    }
}
