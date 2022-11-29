public class User extends AbstractUser {
    private float balance;
    
	public User(int id, String name, String email, String password, float balance) {
        super(id, name, email, password);
        this.balance = balance;
	}
    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
