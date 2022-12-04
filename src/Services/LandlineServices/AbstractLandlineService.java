package Services.LandlineServices;

import Services.AbstractService.AbstractService;
import Services.AbstractService.ServiceForm;
import Services.AbstractService.ServiceHandler;

public abstract class AbstractLandlineService extends AbstractService {
    @Override
    protected ServiceForm creatServiceForm() {
        return new LandlineForm();
    }

    @Override
    protected ServiceHandler creatServiceHandler() {
        return new LandlineHandler();
    }

    @Override
    protected String creatServiceName() {
        return "Landline";
    }

    public abstract void serviceProviderPayLogic();
}