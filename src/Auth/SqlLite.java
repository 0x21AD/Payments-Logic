package Auth;

import java.sql.*;

import Auth.Models.Admin;
import Auth.Models.User;

public class SqlLite {
    private static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // create tables
    public static void createTables() {
        createUserTable();
        createAdminTable();
        registerAdmin("admin", "admin@admin.com", "admin");
    }

    // create user table
    private static void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (\n"
                + "	name text NOT NULL,\n"
                + "	email text PRIMARY KEY,\n"
                + "	password text NOT NULL,\n"
                + "	balance float NOT NULL\n"
                + ");";

        try (Connection conn = SqlLite.Connector();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // create admin table
    private static void createAdminTable() {
        String sql = "CREATE TABLE IF NOT EXISTS admin (\n"
                + "	name text NOT NULL,\n"
                + "	email text PRIMARY KEY,\n"
                + "	password text NOT NULL\n"
                + ");";

        try (Connection conn = SqlLite.Connector();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // create registeration function
    public static boolean registerUser(String name, String email, String password, float balance, boolean admin) {
        String sql = "INSERT INTO user(name,email,password,balance) VALUES(?,?,?,?)";
        try {
            password = Sha1Hasher.encryptPassword(password);
        } catch (Exception e) {
            System.out.println(e);
        }
        try (Connection conn = SqlLite.Connector();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setFloat(4, balance);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            int error = e.getErrorCode();
            System.out.println(error);
            switch (error) {
                case 19:
                    System.out.println("Email already exists");
                    break;
                default:
                    System.out.println(e.getMessage());
                    break;
            }
            return false;
        }
    }

    // create registeration function admin
    public static Boolean registerAdmin(String name, String email, String password) {
        String sql = "INSERT INTO admin(name,email,password) VALUES(?,?,?)";
        try {
            password = Sha1Hasher.encryptPassword(password);
        } catch (Exception e) {
            System.out.println(e);
        }
        try (Connection conn = SqlLite.Connector();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            // int error = e.getErrorCode();
            // System.out.println(error);
            // switch (error) {
            // case 19:
            // System.out.println("Email already exists");
            // break;
            // default:
            // System.out.println(e.getMessage());
            // break;
            // }
            return false;
        }
    }

    // create login function
    public static boolean loginUser(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try {
            password = Sha1Hasher.encryptPassword(password);
        } catch (Exception e) {
            System.out.println(e);
        }
        try (Connection conn = SqlLite.Connector();
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

    // create login function admin
    public static boolean loginAdmin(String email, String password) {
        String sql = "SELECT * FROM admin WHERE email = ? AND password = ?";
        try {
            password = Sha1Hasher.encryptPassword(password);
        } catch (Exception e) {
            System.out.println(e);
        }
        try (Connection conn = SqlLite.Connector();
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

    // retrive user data
    public static User getUserInfo(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";

        try (Connection conn = SqlLite.Connector();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("email"),
                        rs.getString("password"), rs.getFloat("balance"));
                return user;
            }
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1:
                    System.out.println("User not found");
                    break;
                default:
                    System.out.println(e.getMessage());
                    break;
            }
        }
        return null;
    }

    // retrive user data admin
    public static Admin getAdminInfo(String email) {
        String sql = "SELECT * FROM admin WHERE email = ?";

        try (Connection conn = SqlLite.Connector();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin(rs.getString("name"), rs.getString("email"),
                        rs.getString("password"));
                return admin;
            }
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1:
                    System.out.println("User not found");
                    break;
                default:
                    System.out.println(e.getMessage());
                    break;
            }
        }
        return null;
    }

}
