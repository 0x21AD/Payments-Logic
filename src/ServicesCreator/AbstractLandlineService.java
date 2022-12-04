package ServicesCreator;

import ServicesForms.ServiceForm;
import ServicesForms.LandlineForm;
import ServicesHandlers.ServiceHandler;
import ServicesHandlers.LandlineHandler;

public abstract class AbstractLandlineService extends AbstractService {
    @Override
    ServiceForm creatServiceForm() {
        return new LandlineForm();
    }

    @Override
    ServiceHandler creatServiceHandler() {
        return new LandlineHandler();
    }
}