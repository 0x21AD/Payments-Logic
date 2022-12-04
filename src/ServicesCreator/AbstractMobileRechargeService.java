package ServicesCreator;

import ServicesForms.MobileRechargeForm;
import ServicesForms.ServiceForm;
import ServicesHandlers.MobileRechargeHandler;
import ServicesHandlers.ServiceHandler;

public abstract class AbstractMobileRechargeService extends AbstractService {

    @Override
    ServiceForm creatServiceForm() {
        return new MobileRechargeForm();
    }

    @Override
    ServiceHandler creatServiceHandler() {
        return new MobileRechargeHandler();
    }
}
