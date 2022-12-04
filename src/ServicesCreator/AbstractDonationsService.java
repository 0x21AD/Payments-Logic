package ServicesCreator;

import ServicesForms.ServiceForm;
import ServicesForms.DonationsForm;
import ServicesHandlers.ServiceHandler;
import ServicesHandlers.DonationsHandler;

public abstract class AbstractDonationsService extends AbstractService {
    @Override
    ServiceForm creatServiceForm() {
        return new DonationsForm();
    }

    @Override
    ServiceHandler creatServiceHandler() {
        return new DonationsHandler();
    }
}