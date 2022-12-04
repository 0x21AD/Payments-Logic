package Services.InternetServices;

public class WeInternet extends AbstractInternetPaymentService {

    @Override
    public void serviceProviderPayLogic() {
        serviceHandler.pay("http://www.we.com/internet/pay", serviceForm);

    }

    @Override
    public float serviceProviderGetBillLogic() {
        float billAmount = serviceHandler.getBill("http://www.we.com/internet/getbill", serviceForm);
        return billAmount;

    }

    @Override
    protected String creatserviceProviderName() {
        return "We Internet";
    }

    @Override
    protected Boolean allowCod() {
        return false;
    }

}
