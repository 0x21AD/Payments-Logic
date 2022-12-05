package Services.DonationsServices;

import Services.AbstractService.ServiceForm;
import java.util.Scanner;

//Concrete Product A
public class DonationsForm extends ServiceForm {
    String DonationAccount;
    String DonationAmount;

    public String getDonationAmount() {
        return DonationAmount;
    }

    DonationsForm(String DonationAccount) {
        this.DonationAccount = DonationAccount;
    }

    public String getDonationAccount() {
        return DonationAccount;
    }

    @Override
    public void PrintForm() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter your donation amount");
        DonationAmount = sc.nextLine();

    }

}
