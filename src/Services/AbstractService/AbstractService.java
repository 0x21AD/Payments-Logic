package Services.AbstractService;

import java.util.ArrayList;
import Auth.Models.User;
import Discount.Discount;
import Discount.DiscountType;
import Payment.*;
import RuntimeData.DataStoreRuntime;
import UI.InputValidator;

public abstract class AbstractService {
    protected String serviceName = creatServiceName();
    protected String serviceProviderName = creatserviceProviderName();
    protected Boolean COD = allowCod();
    protected ArrayList<Discount> discounts = new ArrayList<Discount>();

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

    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    protected Boolean getCOD() {
        return COD;
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
        Boolean option = InputValidator.validateInputPayBill();
        if (option) {
            Payment payment = paymentMethod();
            if (payment.checkBalanceAndPay(billAmount)) {
                DataStoreRuntime.getInstance().addTransaction(new Transaction(this, billAmount));
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

    private Payment paymentMethod() {
        int paymentMethod = InputValidator.validateInputPaymentMenu(getCOD());
        if (paymentMethod == 1) {
            return new CreditCardPayment();
        } else if (paymentMethod == 2) {
            return new BalancePayment();
        } else {
            return new CashOnDelivery();
        }
    }

    protected abstract float serviceProviderGetBillLogic();

    protected abstract void serviceProviderPayLogic();
}