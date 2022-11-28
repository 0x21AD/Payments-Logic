//connection to database sqlite class using java


//import jdbc driver
import java.sql.*;

public class sqlliteConnection {
    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    //create user table
    public static void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	email text NOT NULL,\n"
                + "	password text NOT NULL\n"
                + "	balance integer NOT NULL\n"
                + " admin Boolean NOT NULL\n"
                + ");";

        try (Connection conn = sqlliteConnection.Connector();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //create registeration function
    public static void register(String name, String email, String password, float balance, boolean admin) {
        String sql = "INSERT INTO user(name,email,password,balance,admin) VALUES(?,?,?,?,?)";

        try (Connection conn = sqlliteConnection.Connector();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setFloat(4, balance);
            pstmt.setBoolean(5, admin);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //create login function
    public static boolean login(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection conn = sqlliteConnection.Connector();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    //retrive user data
    public static User getUser(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";

        try (Connection conn = sqlliteConnection.Connector();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getFloat("balance"), rs.getBoolean("admin"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //update user data

    //create user class
    public static class User {
        private int id;
        private String name;
        private String email;
        private String password;
        private float balance;
        private boolean admin;

        public User(int id, String name, String email, String password, float balance, boolean admin) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
            this.balance = balance;
            this.admin = admin;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public float getBalance() {
            return balance;
        }

        public void setBalance(float balance) {
            this.balance = balance;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }
    }
    

    

}

