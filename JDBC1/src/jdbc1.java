import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class jdbc1 {
    public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;
        String url = "jdbc:oracle:thin:@//localhost:1521/orclpdb1";
        String username = "LOCALUSER1";
        String password = "OracleHomeUser1";

        try {
            // Step 1: Load Driver (optional for newer versions)
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Step 2: Create Connection
            con = DriverManager.getConnection(url,username,password// replace with your DB password
            );

            System.out.println("Connected Successfully ");

            // Step 3: Create Statement
            stmt = con.createStatement();

            // Step 4: SQL Query
            // nth highesh sal
            //String query = "SELECT esal FROM employee ORDER BY esal DESC OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY";;
            //highes sal
            //String query = "SELECT esal FROM employee WHERE esal = (SELECT MAX(esal) FROM employee)";
            String query=("");
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Highest Salary: " + rs.getInt("esal"));
            }

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED ❌");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}