package Auth.Models;

import java.util.ArrayList;
import java.util.Scanner;

import Payment.Transaction;
import UI.DataStoreRuntime;
import UI.MainMenuView;

public class Admin extends AbstractUser {

    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    public void adminPanel() {
        int option;
        int option2;

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Admin Panel");
        System.out.println("1. List all refund requests\n2. Add Discounts\n3. logout");
        try {
            option = sc.nextInt();
            if (option == 1) {
                ArrayList<Transaction> refundTransactions = DataStoreRuntime.getInstance().getRefundServices();
                for (int i = 0; i < refundTransactions.size(); i++) {
                    System.out.println(i + 1 + ". " + refundTransactions.get(i).getUser().getEmail() + " "
                            + refundTransactions.get(i).getService().getServiceProviderName() + " "
                            + refundTransactions.get(i).getAmount());
                }
                System.out.println("0. go back");
                System.out.println("Select a Refund request");
                option2 = sc.nextInt();
                if (option2 == 0) {
                    adminPanel();
                }
                while (option2 > refundTransactions.size() || option2 < 0) {
                    System.out.println("Invalid option");
                    for (int i = 0; i < refundTransactions.size(); i++) {
                        System.out.println(i + 1 + ". " + refundTransactions.get(i).getUser().getEmail() + " "
                                + refundTransactions.get(i).getService().getServiceProviderName() + " "
                                + refundTransactions.get(i).getAmount());
                    }
                    System.out.println("0. go back");
                    System.out.print("Select a Refund request:");
                    option2 = sc.nextInt();
                    if (option2 == 0) {
                        adminPanel();
                    }
                }

                System.out.println("1. Accept\n2. Reject");
                option = sc.nextInt();
                if (option == 1) {
                    refundTransactions.get(option2 - 1).getUser()
                            .addBalance(refundTransactions.get(option2 - 1).getAmount());
                    Transaction transaction = refundTransactions.get(option2 - 1);
                    DataStoreRuntime.getInstance().removeRefund(transaction);
                    DataStoreRuntime.getInstance().removeTransaction(transaction);
                    System.out.println("Refund request accepted");
                } else if (option == 2) {
                    DataStoreRuntime.getInstance().removeRefund(refundTransactions.get(option2 - 1));
                    System.out.println("Refund request rejected");
                }
                adminPanel();
            } else if (option == 2) {
                System.out.println("1. Overall Discount \n2. Specific Discount \n3. GoBack");
                option2 = sc.nextInt();
                while (option2 < 1 || option2 > 3) {
                    System.out.println("Invalid option");
                    System.out.println("1. Overall Discount \n2. Specific Discount \n3. GoBack");
                    option2 = sc.nextInt();
                }
                if (option2 == 1) {
                    System.out.println("Enter the Discount percentage");
                    sc.nextInt();

                } else if (option2 == 2) {

                } else if (option2 == 3) {
                    adminPanel();
                }

            } else if (option == 3) {
                MainMenuView.displayAuthMenu();
            } else {
                System.out.println("Invalid option!");
                adminPanel();
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
            adminPanel();
        }
    }

}
