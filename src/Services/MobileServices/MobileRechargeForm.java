package Services.MobileServices;

import java.util.Scanner;

import Services.AbstractService.ServiceForm;

public class MobileRechargeForm extends ServiceForm {
    private String mobileNumber;
    private String rechargeAmount;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(String amount) {
        this.rechargeAmount = amount;
    }

    @Override
    public void PrintForm() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Mobile Number:");
        mobileNumber = sc.nextLine();
        System.out.println("Enter the Amount of Recharge Money:");
        rechargeAmount = sc.nextLine();
    }

}
