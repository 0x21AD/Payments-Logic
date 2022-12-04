package Services.MobileServices;

public class OrangeMobileRecharge extends AbstractMobileRechargeService {

    @Override
    public void serviceProviderPayLogic() {
        serviceHandler.pay("http://www.orange.com/mobile/pay", serviceForm);

    }

    @Override
    public float serviceProviderGetBillLogic() {
        float billAmount = serviceHandler.getBill("http://www.orange.com/mobile/getbill", serviceForm);
        return billAmount;

    }

    @Override
    protected String creatserviceProviderName() {
        return "Orange Mobile";
    }

}
