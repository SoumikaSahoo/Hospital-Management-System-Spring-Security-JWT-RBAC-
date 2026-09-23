import java.sql.*;

public class preparedstatement {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement ps = null;

        String url = "jdbc:oracle:thin:@//localhost:1521/orclpdb1";
        String username = "LOCALUSER1";
        String password = "OracleHomeUser1";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, username, password);

            System.out.println("Connected Successfully ");

            // Better query with column names
            String query = "INSERT INTO employee  VALUES (?,?,?,?)";

            ps = con.prepareStatement(query);

            ps.setInt(1, 1022);
            ps.setString(2, "harsha");
            ps.setDouble(3, 100000);
            ps.setString(4, "lucknow");
            xz

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Data inserted successfully ");
            }

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED");
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}