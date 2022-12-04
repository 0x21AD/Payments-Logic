package Services.MobileServices;

public class VodafoneMobileRecharge extends AbstractMobileRechargeService {

    @Override
    public void serviceProviderPayLogic() {
        serviceHandler.pay("http://www.vodafone.com/mobile/pay", serviceForm);

    }

    @Override
    public float serviceProviderGetBillLogic() {
        float billAmount = serviceHandler.getBill("http://www.vodafone.com/mobile/getbill", serviceForm);
        return billAmount;

    }

    @Override
    protected String creatserviceProviderName() {
        return "Vodafone Mobile";
    }

}
