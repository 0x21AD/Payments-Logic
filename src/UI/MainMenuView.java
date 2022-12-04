package UI;

import java.util.ArrayList;
import java.util.Scanner;
import Auth.SqlLite;
import Auth.Handlers.AdminAuthHandler;
import Auth.Handlers.AuthHandler;
import Auth.Handlers.UserAuthHandler;
import Auth.Models.Admin;
import Auth.Models.User;
import Services.AbstractService.AbstractService;

public class MainMenuView {
    public static void displayAuthMenu() {
        SqlLite.createTables();
        AuthHandler auth;
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Admin login \n2. User login \n3. Register User account \n4. Exit program");
        System.out.println("Input your desired login panel: ");
        int option = sc.nextInt();

        if (option == 1) {
            System.out.println("Enter your email: ");
            String email = sc.next();
            System.out.println("Enter your password: ");
            String password = sc.next();
            auth = new AdminAuthHandler();
            if (auth.login(email, password)) {
                System.out.println("Login successful");
                Admin admin = (Admin) auth.getUserInfo(email);
                admin.adminPanel();
            } else {
                System.out.println("Login failed");
            }
        } else if (option == 2) {
            System.out.println("Enter your email: ");
            String email = sc.next();
            System.out.println("Enter your password: ");
            String password = sc.next();
            auth = new UserAuthHandler();
            if (auth.login(email, password)) {
                System.out.println("Login successful");
                User user = (User) auth.getUserInfo(email);
                user.userPanel();
            } else {
                System.out.println("Login failed");
            }
        } else if (option == 3) {
            System.out.println("Enter your name: ");
            String name = sc.next();
            System.out.println("Enter your email: ");
            String email = sc.next();
            System.out.println("Enter your password: ");
            String password = sc.next();
            auth = new UserAuthHandler();
            if (auth.Register(name, email, password)) {
                System.out.println("Registration successful");
            } else {
                System.out.println("Registration failed");
            }
        } else if (option == 4) {
            System.out.println("Exiting program!");
            System.exit(0);
        } else {
            System.out.println("Invalid option");
            displayAuthMenu();
        }
    }

    public static void showServices() {
        int option, selectedService;
        String search;
        System.out.println("Avaliable Services:");
        System.out.println(
                "1. Mobile Recharge Service \n2. Internet Recharge Service \n3. Landline Recharge Service \n4. Donations Services\n5. search for a service\n6.exit Menu");
        System.out.println("Choose a Service: ");
        Scanner sc = new Scanner(System.in);
        option = sc.nextInt();
        ArrayList<AbstractService> services = UIHandler.getInstance().getServices();
        ArrayList<AbstractService> filteredBySearch = new ArrayList<AbstractService>();
        if (option == 1) {
            filteredBySearch.clear();
            for (AbstractService service : services) {
                if (service.getServiceName() == "Mobile Recharge") {
                    filteredBySearch.add(service);
                    System.out.println(filteredBySearch.size() + ". " + service.getServiceProviderName());
                }
            }
            System.out.print("Choose your provider:");
            selectedService = sc.nextInt();
            while (selectedService > filteredBySearch.size() || selectedService == 0) {
                System.out.println("Invalid option");
                for (int i = 0; i < filteredBySearch.size(); i++) {
                    System.out.println(i + 1 + ". " + filteredBySearch.get(i).getServiceProviderName());
                }
                System.out.print("Choose your provider:");
                selectedService = sc.nextInt();
            }
            filteredBySearch.get(selectedService - 1).pay();
            showServices();
        } else if (option == 2) {
            filteredBySearch.clear();
            for (AbstractService service : services) {
                if (service.getServiceName() == "Internet Payment") {
                    filteredBySearch.add(service);
                    System.out.println(filteredBySearch.size() + ". " + service.getServiceProviderName());
                }
            }
            System.out.print("Choose your provider:");
            selectedService = sc.nextInt();
            while (selectedService > filteredBySearch.size() || selectedService == 0) {
                System.out.println("Invalid option");
                for (int i = 0; i < filteredBySearch.size(); i++) {
                    System.out.println(i + 1 + ". " + filteredBySearch.get(i).getServiceProviderName());
                }
                System.out.print("Choose your provider:");
                selectedService = sc.nextInt();
            }
            filteredBySearch.get(selectedService - 1).pay();
            showServices();
        } else if (option == 3) {
            filteredBySearch.clear();
            for (AbstractService service : services) {
                if (service.getServiceName() == "Landline") {
                    filteredBySearch.add(service);
                    System.out.println(filteredBySearch.size() + ". " + service.getServiceProviderName());
                }
            }
            System.out.print("Choose your provider:");
            selectedService = sc.nextInt();
            while (selectedService > filteredBySearch.size() || selectedService == 0) {
                System.out.println("Invalid option");
                for (int i = 0; i < filteredBySearch.size(); i++) {
                    System.out.println(i + 1 + ". " + filteredBySearch.get(i).getServiceProviderName());
                }
                System.out.print("Choose your provider:");
                selectedService = sc.nextInt();
            }
            filteredBySearch.get(selectedService - 1).pay();
            showServices();
        } else if (option == 4) {
            filteredBySearch.clear();
            for (AbstractService service : services) {
                if (service.getServiceName() == "Donations") {
                    filteredBySearch.add(service);
                    System.out.println(filteredBySearch.size() + ". " + service.getServiceProviderName());
                }
            }
            System.out.print("Choose your provider:");
            selectedService = sc.nextInt();
            while (selectedService > filteredBySearch.size() || selectedService == 0) {
                System.out.println("Invalid option");
                for (int i = 0; i < filteredBySearch.size(); i++) {
                    System.out.println(i + 1 + ". " + filteredBySearch.get(i).getServiceProviderName());
                }
                System.out.print("Choose your provider:");
                selectedService = sc.nextInt();
            }
            filteredBySearch.get(selectedService - 1).pay();
            showServices();
        } else if (option == 5) {
            filteredBySearch.clear();
            System.out.println("Enter the name of the service provider you want to search for");
            Scanner sc1 = new Scanner(System.in);
            filteredBySearch.clear();
            search = sc1.nextLine();
            for (AbstractService service : services) {
                if (service.getServiceProviderName().contains(search)) {
                    filteredBySearch.add(service);
                    System.out.println(filteredBySearch.size() + ". " + service.getServiceProviderName());
                }
            }
            System.out.print("Choose your provider:");
            selectedService = sc.nextInt();
            while (selectedService > filteredBySearch.size() || selectedService == 0) {
                System.out.println("Invalid option");
                for (int i = 0; i < filteredBySearch.size(); i++) {
                    System.out.println(i + 1 + ". " + filteredBySearch.get(i).getServiceProviderName());
                }
                System.out.print("Choose your provider:");
                selectedService = sc.nextInt();
            }
            filteredBySearch.get(selectedService - 1).pay();
            showServices();
        } else if (option == 6) {
            System.out.println("Going Back!");
            User.UserOptionsMenu();
        } else {
            System.out.println("Invalid option!");
        }

    }

}
