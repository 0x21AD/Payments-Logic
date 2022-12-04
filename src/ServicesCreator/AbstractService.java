package ServicesCreator;

import ServicesForms.ServiceForm;
import ServicesHandlers.ServiceHandler;

public abstract class AbstractService {
    ServiceForm serviceForm = creatServiceForm();
    ServiceHandler serviceHandler = creatServiceHandler();

    abstract ServiceForm creatServiceForm();

    abstract ServiceHandler creatServiceHandler();
}
