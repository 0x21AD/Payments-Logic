package Payment;

//Strategy Concrete Class
public class CashOnDelivery implements Payment {

    @Override
    public Boolean checkBalanceAndPay(float amount) {
        return true;
    }

}
