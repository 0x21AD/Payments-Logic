package Services.LandlineServices;

import java.util.Scanner;

import Services.AbstractService.ServiceForm;

public class LandlineForm extends ServiceForm {
    private String LandLineNumber;

    public String getLandLineNumber() {
        return LandLineNumber;
    }

    public void setLandLineNumber(String landLineNumber) {
        LandLineNumber = landLineNumber;
    }

    @Override
    public void PrintForm() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Landline Number:");
        LandLineNumber = sc.nextLine();
    }

}
