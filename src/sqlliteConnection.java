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
                + "	name text NOT NULL,\n"
                + "	email text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + "	balance float NOT NULL\n"
                + ");";

        try (Connection conn = sqlliteConnection.Connector();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //create admin table
    public static void createAdminTable() {
        String sql = "CREATE TABLE IF NOT EXISTS admin (\n"
                + "	name text NOT NULL,\n"
                + "	email text PRIMARY KEY,\n"
                + "	password text NOT NULL\n"
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
        String sql = "INSERT INTO user(name,email,password,balance) VALUES(?,?,?,?)";

        try (Connection conn = sqlliteConnection.Connector();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setFloat(4, balance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            String error = e.getMessage();
            if (error.equals("[SQLITE_CONSTRAINT_PRIMARYKEY]  A PRIMARY KEY constraint failed (UNIQUE constraint failed: user.email)")){
                System.out.println("Email already exists.");
            }else{
                System.out.println(error);
            }
        }
    }

    //create registeration function admin
    public static void registerAdmin(String name, String email, String password) {
        String sql = "INSERT INTO admin(name,email,password) VALUES(?,?,?)";

        try (Connection conn = sqlliteConnection.Connector();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            String error = e.getMessage();
            if (error.equals("[SQLITE_CONSTRAINT_PRIMARYKEY]  A PRIMARY KEY constraint failed (UNIQUE constraint failed: admin.email)")){
                System.out.println("Email already exists.");
            }else{
                System.out.println(error);
            }
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

    //create login function admin
    public static boolean loginAdmin(String email, String password) {
        String sql = "SELECT * FROM admin WHERE email = ? AND password = ?";

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
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                        rs.getString("password"), rs.getFloat("balance"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    //retrive user data admin
    public static Admin getAdmin(String email) {
        String sql = "SELECT * FROM admin WHERE email = ?";

        try (Connection conn = sqlliteConnection.Connector();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                        rs.getString("password"));
                return admin;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}

