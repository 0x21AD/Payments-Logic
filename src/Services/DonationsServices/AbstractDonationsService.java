package Services.DonationsServices;

import Services.AbstractService.AbstractService;
import Services.AbstractService.ServiceForm;
import Services.AbstractService.ServiceHandler;

public abstract class AbstractDonationsService extends AbstractService {

    abstract String getDonationAccount();

    @Override
    protected ServiceForm creatServiceForm() {
        return new DonationsForm(getDonationAccount());
    }

    @Override
    protected ServiceHandler creatServiceHandler() {
        return new DonationsHandler();
    }

    @Override
    protected String creatServiceName() {
        return "Donations";
    }
}