import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbExam4 {
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
            String sql = "SELECT * FROM products "
            		+ "WHERE product_id = ? OR product_name = ?"
            		+ "ORDER BY product_id";

            // create statement
            stmt = con.prepareStatement(sql);
            
            int param = 101;
            String param2 = "地球儀";
            stmt.setInt(1, param);
            stmt.setString(2, param2);
            // execute
            ResultSet rs = stmt.executeQuery();

            //output
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                String price = rs.getString("price");

                System.out.print("product_id:" + product_id + ", ");
                System.out.print("product_name:" + product_name + ", ");
                System.out.println("price:" + price);
            }
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
