package Services.AbstractService;

import java.util.ArrayList;
import java.util.Scanner;

import Auth.Models.User;
import Discount.Discount;
import Discount.DiscountType;
import Payment.*;
import UI.DataStoreRuntime;

public abstract class AbstractService {
    protected String serviceName = creatServiceName();
    protected String serviceProviderName = creatserviceProviderName();
    protected Boolean COD = allowCod();
    protected ArrayList<Discount> discounts = new ArrayList<Discount>();

    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    protected Boolean getCOD() {
        return COD;
    }

    protected ServiceForm serviceForm = creatServiceForm();
    protected ServiceHandler serviceHandler = creatServiceHandler();

    protected abstract ServiceForm creatServiceForm();

    protected abstract ServiceHandler creatServiceHandler();

    protected abstract String creatServiceName();

    protected abstract String creatserviceProviderName();

    protected abstract Boolean allowCod();

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceProviderName() {
        return serviceProviderName;
    }

    public void notifyAddDiscount(Discount discount) {
        discounts.add(discount);

    }

    public void notifyRemoveDiscount(Discount discount) {
        discounts.remove(discount);

    }

    public final void pay() {
        serviceForm.PrintForm();
        float billAmount = serviceProviderGetBillLogic();
        System.out.println("The bill amount is " + billAmount);
        if (applyDiscount(billAmount) != billAmount) {
            billAmount = applyDiscount(billAmount);
            System.out.println("The bill amount after discount is " + billAmount);
        }
        System.out.println("Do you wish to pay the bill ?(y/n)");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        if (option.equals("y") || option.equals("yes") || option.equals("y")) { // could be handled by regex later.
            Payment payment = printPaymentMenu();
            if (payment.checkBalanceAndPay(billAmount)) {
                Transaction transaction = new Transaction(this, billAmount);
                DataStoreRuntime.getInstance().addTransaction(transaction);
                System.out.println("Payment successful");
                serviceProviderPayLogic();
            } else {
                System.out.println("Payment failed");
                System.out.println("Insufficient balance");
            }
        } else {
            System.out.println("You have not paid the bill");
        }
    }

    public float applyDiscount(float amount) {
        float discountAmount = 0;
        for (Discount discount : discounts) {
            if (discount.getDiscountType().equals(DiscountType.Overall)
                    && !User.getInstance().getTransactions().isEmpty()) {
                continue;
            }
            discountAmount += discount.getDiscount();
        }
        return amount - (discountAmount * amount);
    }

    private Payment printPaymentMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("choose payment method: ");
        System.out.println("1- Credit Card");
        System.out.println("2- Personal Wallet");
        if (getCOD()) {
            System.out.println("3- Cash on delivery");
        }
        int paymentMethod = sc.nextInt();
        if (paymentMethod == 1) {
            return new CreditCardPayment();
        } else if (paymentMethod == 2) {
            return new BalancePayment();
        } else if (paymentMethod == 3 && getCOD()) {
            return new CashOnDelivery();
        } else {
            System.out.println("Invalid payment method");
            return printPaymentMenu();
        }
    }

    protected abstract float serviceProviderGetBillLogic();

    protected abstract void serviceProviderPayLogic();
}