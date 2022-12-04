package Services.InternetServices;

public class OrangeInternet extends AbstractInternetPaymentService {

    @Override
    public void serviceProviderPayLogic() {
        serviceHandler.pay("http://www.orange.com/internet/pay", serviceForm);

    }

    @Override
    public float serviceProviderGetBillLogic() {
        float billAmount = serviceHandler.getBill("http://www.orange.com/internet/getbill", serviceForm);
        return billAmount;

    }

    @Override
    protected String creatserviceProviderName() {
        return "Orange Internet";
    }

}
