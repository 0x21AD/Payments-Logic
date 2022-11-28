//import java.sql.*;
//public class AbstractDatabase{
//    public void Testing(){
//        System.out.println("Testing Database class");
//    }
//
//    public void DBconnection() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/fawryreplica", "root", "");
//            //here sonoo is database name, root is username and password  
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from users");
//            while (rs.next())
//                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//}