package Auth.Models;

public class Admin extends AbstractUser {

    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    public void adminPanel() {
        System.out.println("Welcome to Admin Panel");

    }

}
