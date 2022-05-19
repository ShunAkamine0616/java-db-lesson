import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbDevExam4_Insert {
	 public static void main(String[] args) {
	        Connection con = null;
	        PreparedStatement stmt = null;

	        try {
	            // load JDBC Driver
	            Class.forName("org.postgresql.Driver");

	            // confirm
	            System.out.println(" --- before connection --- ");

	            // database connect
	            con = DriverManager.getConnection("jdbc:postgresql:dbconnection", "axizuser", "axiz");

	            // confirm
	            System.out.println(" --- after connection --- ");

	            // SQL query string
	            String sql = "INSERT INTO products(product_name, price)"
	            		+ "VALUES(?, ?)";

	            // create statement
	            stmt = con.prepareStatement(sql);
	            
	            String param = "ボールペン";
	            int param2 = 200;
	            stmt.setString(1, param);
	            stmt.setInt(2, param2);
	            // execute
	            stmt.executeUpdate();
	            System.out.println("登録しました");
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // close
	            if (stmt != null) {
	                try {
	                    stmt.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }

	            if (con != null) {
	                try {
	                    con.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
}
