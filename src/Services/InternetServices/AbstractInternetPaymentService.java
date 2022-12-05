package Services.InternetServices;

import Services.AbstractService.AbstractService;
import Services.AbstractService.ServiceForm;
import Services.AbstractService.ServiceHandler;

//Factory Creator Class Service
public abstract class AbstractInternetPaymentService extends AbstractService {
    @Override
    protected ServiceForm creatServiceForm() {
        return new InternetPaymentForm();
    }

    @Override
    protected ServiceHandler creatServiceHandler() {
        return new InternetPaymentHandler();
    }

    @Override
    protected String creatServiceName() {
        return "Internet Payment";
    }
}