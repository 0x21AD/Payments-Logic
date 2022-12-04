package Services.AbstractService;

import java.util.Scanner;

public abstract class AbstractService {
    public String serviceName = creatServiceName();
    public String serviceProviderName = creatserviceProviderName();
    public ServiceForm serviceForm = creatServiceForm();
    public ServiceHandler serviceHandler = creatServiceHandler();

    protected abstract ServiceForm creatServiceForm();

    protected abstract ServiceHandler creatServiceHandler();

    protected abstract String creatServiceName();

    protected abstract String creatserviceProviderName();

    public final void pay() {
        serviceForm.PrintForm();
        float billAmount = serviceProviderGetBillLogic();
        System.out.println("The bill amount is " + billAmount);
        System.out.println("Do you wish to pay the bill ?(y/n)");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        if (option.equals("y") || option.equals("yes") || option.equals("y")) { // could be handled by regex later.
            // check amount of money in the account
            // if (payment.checkBalance(billAmount)) {
            // serviceProviderPayLogic();
            // } else {
            // System.out.println("You don't have enough money in your account");
            // }
        } else {
            System.out.println("You have not paid the bill");
        }
    }

    public abstract float serviceProviderGetBillLogic();

    public abstract void serviceProviderPayLogic();
}