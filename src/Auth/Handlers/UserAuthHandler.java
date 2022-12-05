package Auth.Handlers;

import Auth.SqlLite;
import Auth.Models.AbstractUser;

//Strategy Concrete Class
public class UserAuthHandler implements AuthHandler {

    @Override
    public Boolean Register(String name, String email, String password) {
        return SqlLite.registerUser(name, email, password, 0, false);
    }

    @Override
    public Boolean login(String email, String password) {
        return SqlLite.loginUser(email, password);
    }

    @Override
    public AbstractUser getUserInfo(String email) {
        return SqlLite.getUserInfo(email);
    }

}
