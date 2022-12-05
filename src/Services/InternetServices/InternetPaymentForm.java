package Services.InternetServices;

import java.util.Scanner;

import Services.AbstractService.ServiceForm;

//Concrete Product A
public class InternetPaymentForm extends ServiceForm {

    private String LandLineNumber;
    private float BillAmount;

    public String getLandLineNumber() {
        return LandLineNumber;
    }

    public void setLandLineNumber(String landLineNumber) {
        LandLineNumber = landLineNumber;
    }

    public float getBillAmount() {
        return BillAmount;
    }

    public void setBillAmount(float billAmount) {
        BillAmount = billAmount;
    }

    @Override
    public void PrintForm() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Landline Number:");
        LandLineNumber = sc.nextLine();
    }

}
