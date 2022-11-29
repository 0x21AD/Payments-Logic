public class UserAuthHandler implements AuthHandler {

    @Override
    public Boolean Register(String name, String email, String password) {
        return sqlliteConnection.registerUser(name, email, password, 0, false);
    }

    @Override
    public Boolean login(String email, String password) {
        return sqlliteConnection.loginUser(email, password);
    }

    @Override
    public AbstractUser getUserInfo(String email) {
        return sqlliteConnection.getUserInfo(email);
    }
    
}
