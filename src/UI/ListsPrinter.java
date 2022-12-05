package UI;

import java.util.ArrayList;

import Discount.Discount;
import Discount.DiscountType;
import Payment.Transaction;
import Services.AbstractService.AbstractService;

public class ListsPrinter {
    public static void printServices(ArrayList<String> services) {
        for (int i = 0; i < services.size(); i++) {
            System.out.println(i + 1 + ". " + services.get(i));
        }
    }

    public static void printServicesProviders(ArrayList<AbstractService> services) {
        if (services.size() == 0) {
            System.out.println("No Service found");
        }
        for (int i = 0; i < services.size(); i++) {
            System.out.println(i + 1 + ". " + services.get(i).getServiceProviderName());
        }
    }

    public static void printDiscounts(ArrayList<Discount> discounts, DiscountType discountType) {
        for (int i = 0; i < discounts.size(); i++) {
            if (discounts.get(i).getDiscountType() == discountType) {
                System.out.println(i + 1 + ". " + discounts.get(i).getDiscount());
            }
        }
    }

    public static void printTransactions(ArrayList<Transaction> transactions) {
        if (transactions.size() == 0) {
            System.out.println("No transactions yet");
        }
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(i + 1 + ". " + transactions.get(i).getService().getServiceProviderName() + " "
                    + transactions.get(i).getAmount() + "$");
        }
    }

    public static void printRefundTransactions(ArrayList<Transaction> refundTransactions) {
        if (refundTransactions.size() == 0) {
            System.out.println("No Refund requests yet");
        }
        for (int i = 0; i < refundTransactions.size(); i++) {
            System.out.println(i + 1 + ". " + refundTransactions.get(i).getUser().getEmail() + " "
                    + refundTransactions.get(i).getService().getServiceProviderName() + " "
                    + refundTransactions.get(i).getAmount());
        }
    }

}
