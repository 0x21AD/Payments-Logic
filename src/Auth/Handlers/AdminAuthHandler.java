package Auth.Handlers;

import Auth.SqlLite;
import Auth.Models.AbstractUser;

//Strategy Concrete Class
public class AdminAuthHandler implements AuthHandler {

    @Override
    public Boolean Register(String name, String email, String password) {
        return SqlLite.registerAdmin(name, email, password);
    }

    @Override
    public Boolean login(String email, String password) {
        return SqlLite.loginAdmin(email, password);
    }

    @Override
    public AbstractUser getUserInfo(String email) {
        return SqlLite.getAdminInfo(email);
    }

}
