package Services.MobileServices;

import Services.AbstractService.ServiceForm;
import Services.AbstractService.ServiceHandler;

//Concrete Product B
public class MobileRechargeHandler extends ServiceHandler {
    @Override
    public void pay(String serviceRequest, ServiceForm serviceForm) {
        System.out.println("You have paid the bill for " + serviceRequest);
        System.out
                .println("You have paid for the mobile service: "
                        + ((MobileRechargeForm) serviceForm).getMobileNumber());
    }

    @Override
    public float getBill(String serviceRequest, ServiceForm serviceForm) {
        System.out.println("You are getting bill amount from  " + serviceRequest);
        float billAmount = Float.parseFloat(((MobileRechargeForm) serviceForm).getRechargeAmount());
        return billAmount;
    }
}
