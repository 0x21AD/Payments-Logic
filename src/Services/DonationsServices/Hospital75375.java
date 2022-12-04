package Services.DonationsServices;

public class Hospital75375 extends AbstractDonationsService {

    @Override
    public void serviceProviderPayLogic() {
        serviceHandler.pay("http://www.Fawry.com/" + getDonationAccount() + "/pay", serviceForm);

    }

    @Override
    final String getDonationAccount() {
        return "75375";
    }

    @Override
    public float serviceProviderGetBillLogic() {
        float billAmount = serviceHandler.getBill("http://www.Fawry.com/" + getDonationAccount() + "/getbill",
                serviceForm);
        return billAmount;

    }

    @Override
    protected String creatserviceProviderName() {
        return "Hospital 75375";
    }

}
