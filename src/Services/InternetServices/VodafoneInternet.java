package Services.InternetServices;

public class VodafoneInternet extends AbstractInternetPaymentService {

    @Override
    public void serviceProviderPayLogic() {
        serviceHandler.pay("http://www.vodafone.com/internet/pay", serviceForm);

    }

    @Override
    public float serviceProviderGetBillLogic() {
        float billAmount = serviceHandler.getBill("http://www.vodafone.com/internet/getbill", serviceForm);
        return billAmount;

    }

    @Override
    protected String creatserviceProviderName() {
        return "Vodafone Internet";
    }

    @Override
    protected Boolean allowCod() {
        return false;
    }

}
