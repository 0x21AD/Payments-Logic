package Auth.Handlers;

import Auth.Models.AbstractUser;

//Strategy Interface
public interface AuthHandler {
    Boolean Register(String name, String email, String password);

    Boolean login(String email, String password);

    AbstractUser getUserInfo(String email);

}
