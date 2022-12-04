package Services.MobileServices;

public class WeMobileRecharge extends AbstractMobileRechargeService {

    @Override
    public void serviceProviderPayLogic() {
        serviceHandler.pay("http://www.we.com/mobile/pay", serviceForm);

    }

    @Override
    public float serviceProviderGetBillLogic() {
        float billAmount = serviceHandler.getBill("http://www.we.com/mobile/getbill", serviceForm);
        return billAmount;
    }

    @Override
    protected String creatserviceProviderName() {
        return "We Mobile";
    }

}
