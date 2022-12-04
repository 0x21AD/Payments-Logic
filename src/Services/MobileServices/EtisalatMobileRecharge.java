package Services.MobileServices;

public class EtisalatMobileRecharge extends AbstractMobileRechargeService {

    @Override
    public void serviceProviderPayLogic() {
        serviceHandler.pay("http://www.etisalat.com/mobile/pay", serviceForm);

    }

    @Override
    public float serviceProviderGetBillLogic() {
        float billAmount = serviceHandler.getBill("http://www.etisalat.com/mobile/getbill", serviceForm);
        return billAmount;

    }

    @Override
    protected String creatserviceProviderName() {
        return "Etisalat Mobile";
    }

    @Override
    protected Boolean allowCod() {
        return false;
    }

}
