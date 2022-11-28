//connection to database sqlite class using java


//import jdbc driver
import java.sql.*;

public class sqlliteConnection {
    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    //create database sqlite
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS student (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " age integer NOT NULL,\n"
                + " address text NOT NULL,\n"
                + " phone text NOT NULL\n"
                + ");";

        try (Connection conn = sqlliteConnection.Connector();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // insert in database using java
    public static void insert(String name, String email, String password) {
        String sql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";
        try {
            Connection conn = Connector();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // update in database using java
    public static void update(String name, String email, String password, int id) {
        String sql = "UPDATE users SET name=?,email=?,password=? WHERE id=?";
        try {
            Connection conn = Connector();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setInt(4, id);
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // delete in database using java
    public static void delete(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try {
            Connection conn = Connector();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

