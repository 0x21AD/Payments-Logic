package UI;

import java.util.ArrayList;

import Services.AbstractService.AbstractService;
import Services.DonationsServices.*;
import Services.InternetServices.*;
import Services.LandlineServices.*;
import Services.MobileServices.*;

public class UIHandler {
    private ArrayList<AbstractService> services = new ArrayList<AbstractService>();

    public ArrayList<AbstractService> getServices() {
        return services;
    }

    private static UIHandler ui = null;

    private UIHandler() {
        services.add(new EtisalatMobileRecharge());
        services.add(new OrangeMobileRecharge());
        services.add(new VodafoneMobileRecharge());
        services.add(new WeMobileRecharge());

        services.add(new EtisalatInternet());
        services.add(new OrangeInternet());
        services.add(new VodafoneInternet());
        services.add(new WeInternet());

        services.add(new LandlineMonthly());
        services.add(new LandlineQuarterYearly());

        services.add(new Hospital75375());
        services.add(new MagdyYaacoubHospital());
    }

    public static UIHandler getInstance() {
        if (ui == null) {
            ui = new UIHandler();
        }
        return ui;
    }

}
