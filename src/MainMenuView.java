import java.util.Scanner;

public class MainMenuView {
    public static void display() {
        sqlliteConnection.createTables();
        AuthHandler auth;
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Admin login \n 2. User login \n 3. Register User account");
        System.out.println("Input your desired login panel: ");
        int option = sc.nextInt();
        if(option == 1){
            System.out.println("Enter your email: ");
            String email = sc.next();
            System.out.println("Enter your password: ");
            String password = sc.next();
            auth = new AdminAuthHandler();
            if(auth.login(email,password)){
                System.out.println("Login successful");
                Admin admin = (Admin)auth.getUserInfo(email);
                admin.adminPanel();
            }
            else{
                System.out.println("Login failed");
            }
        }
        else if(option == 2){
            System.out.println("Enter your email: ");
            String email = sc.next();
            System.out.println("Enter your password: ");
            String password = sc.next();
            auth = new UserAuthHandler();
            if(auth.login(email , password)){
                System.out.println("Login successful");
                User user = (User)auth.getUserInfo(email);
                user.userPanel();
            }
            else{
                System.out.println("Login failed");
            }
        }
        else if (option == 3) {
            System.out.println("Enter your name: ");
            String name = sc.next();
            System.out.println("Enter your email: ");
            String email = sc.next();
            System.out.println("Enter your password: ");
            String password = sc.next();
            auth = new UserAuthHandler();
            if(auth.Register(name,email,password)){
                System.out.println("Registration successful");
            }
            else{
                System.out.println("Registration failed");
            }
        }
        else {
            System.out.println("Invalid option");
            display();
        }
        sc.close();
    }

}
