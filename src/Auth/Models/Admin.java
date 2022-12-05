package Auth.Models;

import java.util.ArrayList;
import java.util.Scanner;

import Discount.Discount;
import Discount.DiscountType;
import Payment.Transaction;
import Services.AbstractService.AbstractService;
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
        System.out.println("1. List all refund requests\n2. Add Discounts\n3. Delete Discounts\n4. logout");
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
                    System.out.println("Enter the Discount percentage (number only)");
                    option = sc.nextInt();
                    float discountAmount = (float) option / 100;
                    Discount discount = new Discount(discountAmount, DiscountType.Overall);
                    for (AbstractService service : DataStoreRuntime.getInstance().getServices()) {
                        service.notifyAddDiscount(discount);
                    }
                    System.out.println("Discount Added Successfully");
                    adminPanel();
                } else if (option2 == 2) {
                    System.out.println("Choose a service to apply Discount");
                    ArrayList<AbstractService> services = DataStoreRuntime.getInstance().getServices();
                    for (int i = 0; i < services.size(); i++) {
                        System.out.println(i + 1 + ". " + services.get(i).getServiceProviderName());
                    }
                    System.out.println("0. Go Back");
                    option = sc.nextInt();
                    if (option == 0) {
                        adminPanel();
                    }
                    while (option < 0 || option > services.size()) {
                        System.out.println("Invalid option");
                        System.out.println("Choose a service to apply Discount");
                        for (int i = 0; i < services.size(); i++) {
                            System.out.println(i + 1 + ". " + services.get(i).getServiceProviderName());
                        }
                        System.out.println("0. Go Back");
                        option = sc.nextInt();
                        if (option == 0) {
                            adminPanel();
                        }
                    }
                    System.out.println("Enter the Discount percentage (number only)");
                    option2 = sc.nextInt();
                    float discountAmount = (float) option2 / 100;
                    Discount discount = new Discount(discountAmount, DiscountType.Specific);
                    services.get(option - 1).notifyAddDiscount(discount);
                    System.out.println("Discount Added Successfully");
                    adminPanel();
                } else if (option2 == 3) {
                    adminPanel();
                }

            } else if (option == 3) {
                // delete dicount
                System.out.println("1. Overall Discount \n2. Specific Discount \n3. GoBack");
                option2 = sc.nextInt();
                while (option2 < 1 || option2 > 3) {
                    System.out.println("Invalid option");
                    System.out.println("1. Overall Discount \n2. Specific Discount \n3. GoBack");
                    option2 = sc.nextInt();
                }
                if (option2 == 1) {
                    ArrayList<Discount> discount = DataStoreRuntime.getInstance().getServices().get(0).getDiscounts();
                    for (int i = 0; i < discount.size(); i++) {
                        if (discount.get(i).getDiscountType() == DiscountType.Overall) {
                            System.out.println(i + 1 + ". " + discount.get(i).getDiscount());
                        }
                    }
                    System.out.println("0. Go Back");
                    option = sc.nextInt();
                    if (option == 0) {
                        adminPanel();
                    }
                    while (option < 0 || option > discount.size()) {
                        System.out.println("Invalid option");
                        for (int i = 0; i < discount.size(); i++) {
                            if (discount.get(i).getDiscountType() == DiscountType.Overall) {
                                System.out.println(i + 1 + ". " + discount.get(i).getDiscount());
                            }
                        }
                        System.out.println("0. Go Back");
                        option = sc.nextInt();
                        if (option == 0) {
                            adminPanel();
                        }
                    }
                    for (AbstractService service : DataStoreRuntime.getInstance().getServices()) {
                        service.notifyRemoveDiscount(discount.get(option - 1));
                    }
                    System.out.println("Discount Removed Successfully");
                    adminPanel();
                } else if (option2 == 2) {
                    System.out.println("Choose a service to remove Discount");
                    ArrayList<AbstractService> services = DataStoreRuntime.getInstance().getServices();
                    for (int i = 0; i < services.size(); i++) {
                        System.out.println(i + 1 + ". " + services.get(i).getServiceProviderName());
                    }
                    System.out.println("0. Go Back");
                    option = sc.nextInt();
                    if (option == 0) {
                        adminPanel();
                    }
                    while (option < 0 || option > services.size()) {
                        System.out.println("Invalid option");
                        System.out.println("Choose a service to remove Discount");
                        for (int i = 0; i < services.size(); i++) {
                            System.out.println(i + 1 + ". " + services.get(i).getServiceProviderName());
                        }
                        System.out.println("0. Go Back");
                        option = sc.nextInt();
                        if (option == 0) {
                            adminPanel();
                        }
                    }
                    ArrayList<Discount> discount = DataStoreRuntime.getInstance().getServices().get(option - 1)
                            .getDiscounts();
                    for (int i = 0; i < discount.size(); i++) {
                        if (discount.get(i).getDiscountType() == DiscountType.Specific) {
                            System.out.println(i + 1 + ". " + discount.get(i).getDiscount());
                        }
                    }
                    System.out.println("0. Go Back");
                    option2 = sc.nextInt();
                    if (option2 == 0) {
                        adminPanel();
                    }
                    while (option2 < 0 || option2 > discount.size()) {
                        System.out.println("Invalid option");
                        for (int i = 0; i < discount.size(); i++) {
                            if (discount.get(i).getDiscountType() == DiscountType.Overall) {
                                System.out.println(i + 1 + ". " + discount.get(i).getDiscount());
                            }
                        }
                        System.out.println("0. Go Back");
                        option2 = sc.nextInt();
                        if (option2 == 0) {
                            adminPanel();
                        }
                    }
                    services.get(option - 1).notifyRemoveDiscount(discount.get(option2 - 1));
                    System.out.println("Discount Removed Successfully");
                    adminPanel();
                } else if (option2 == 3) {
                    adminPanel();
                }
            } else if (option == 4) {
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
