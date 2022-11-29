public class AdminAuthHandler implements AuthHandler {

    @Override
    public Boolean Register(String name, String email, String password){
        return sqlliteConnection.registerAdmin(name, email, password);
    }

    @Override
    public Boolean login(String email, String password) {
        return sqlliteConnection.loginAdmin(email, password);
    }

    @Override
    public AbstractUser getUserInfo(String email) {
        return sqlliteConnection.getAdminInfo(email);
    }
    
}
