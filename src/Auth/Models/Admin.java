package Auth.Models;

import java.util.ArrayList;
import Discount.Discount;
import Discount.DiscountType;
import Payment.Transaction;
import RuntimeData.DataStoreRuntime;
import Services.AbstractService.AbstractService;
import UI.InputValidator;
import UI.MainMenuView;

public class Admin extends AbstractUser {

    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    public void adminPanel() {
        System.out.println(String.format("Welcome Back %s!", this.getName()));
        adminOptionsMenu();
    }

    public void adminOptionsMenu() {
        DataStoreRuntime dts = DataStoreRuntime.getInstance();
        ArrayList<Transaction> refundTransactions = dts.getRefundServices();
        ArrayList<AbstractService> services = dts.getServices();
        int option2;
        System.out.println("Admin Panel");
        try {
            int option = InputValidator.validateInputAdminMenu();
            if (option == 1) {
                option2 = InputValidator.validateInputRefundTransactions(refundTransactions);
                if (option2 == refundTransactions.size() + 1) {
                    adminPanel();
                }
                Transaction transaction = refundTransactions.get(option2 - 1);
                option = InputValidator.validateInputRefundStatus();
                if (option == 1) {
                    transaction.getUser().addBalance(transaction.getAmount());
                    dts.removeRefund(transaction);
                    dts.removeTransaction(transaction);
                    System.out.println("Refund request accepted");
                } else if (option == 2) {
                    dts.removeRefund(transaction);
                    System.out.println("Refund request rejected");
                }
            } else if (option == 2) {
                option2 = InputValidator.validateInputDiscountsMenu();
                if (option2 == 1) {
                    float discountAmount = InputValidator.validateInputDiscountPercentage();
                    Discount discount = new Discount(discountAmount, DiscountType.Overall);
                    for (AbstractService service : services) {
                        service.notifyAddDiscount(discount);
                    }
                } else if (option2 == 2) {
                    option = InputValidator.validateInputServicesProviders(services);
                    if (option == services.size() + 1) {
                        adminPanel();
                    }
                    float discountAmount = InputValidator.validateInputDiscountPercentage();
                    services.get(option - 1).notifyAddDiscount(new Discount(discountAmount, DiscountType.Specific));
                }
                System.out.println("Discount Added Successfully");
            } else if (option == 3) {
                option2 = InputValidator.validateInputDiscountsMenu();
                if (option2 == 1) {
                    ArrayList<Discount> discount = services.get(0).getDiscounts();
                    option = InputValidator.validateInputDiscounts(discount, DiscountType.Overall);
                    if (option == discount.size() + 1) {
                        adminPanel();
                    }
                    Discount discount2 = discount.get(option - 1);
                    for (AbstractService service : services) {
                        service.notifyRemoveDiscount(discount2);
                    }
                } else if (option2 == 2) {
                    option = InputValidator.validateInputServicesProviders(services);
                    if (option == services.size() + 1) {
                        adminPanel();
                    }
                    ArrayList<Discount> discount = services.get(option - 1).getDiscounts();
                    option2 = InputValidator.validateInputDiscounts(discount, DiscountType.Specific);
                    services.get(option - 1).notifyRemoveDiscount(discount.get(option2 - 1));
                }
                System.out.println("Discount Removed Successfully");
            } else if (option == 4) {
                MainMenuView.displayAuthMenu();
                return;
            }
            adminPanel();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Invalid input");
            adminPanel();
        }
    }

}
