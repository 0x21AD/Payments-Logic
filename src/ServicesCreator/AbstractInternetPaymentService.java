package ServicesCreator;

import ServicesForms.ServiceForm;
import ServicesForms.InternetPaymentForm;
import ServicesHandlers.ServiceHandler;
import ServicesHandlers.InternetPaymentHandler;

public abstract class AbstractInternetPaymentService extends AbstractService {
    @Override
    ServiceForm creatServiceForm() {
        return new InternetPaymentForm();
    }

    @Override
    ServiceHandler creatServiceHandler() {
        return new InternetPaymentHandler();
    }
}