
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        //sqlliteConnection.createUserTable();
        //sqlliteConnection.register("mishoo","mishoopop6@gmail.com","mishoo",80,false);
        System.out.println(sqlliteConnection.login("mishoopop6@gmail.com", "mishoo"));
    }
}
