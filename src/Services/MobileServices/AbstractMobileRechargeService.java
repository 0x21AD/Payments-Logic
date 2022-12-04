package Services.MobileServices;

import Services.AbstractService.AbstractService;
import Services.AbstractService.ServiceForm;
import Services.AbstractService.ServiceHandler;

public abstract class AbstractMobileRechargeService extends AbstractService {

    @Override
    protected ServiceForm creatServiceForm() {
        return new MobileRechargeForm();
    }

    @Override
    protected ServiceHandler creatServiceHandler() {
        return new MobileRechargeHandler();
    }

    @Override
    protected String creatServiceName() {
        return "Mobile Recharge";
    }
}
