package Payment;

import Auth.Models.User;

public class BalancePayment implements Payment {

    @Override
    public Boolean checkBalanceAndPay(float amount) {
        User user = User.getInstance();
        if (user.getBalance() >= amount) {
            user.decreaseBalance(amount);
            return true;
        }
        return false;

    }
}
