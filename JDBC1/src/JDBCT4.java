import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCT4 {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@//localhost:1521/orclpdb1";
        String username = "LOCALUSER1";
        String password = "OracleHomeUser1";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();

            Scanner sc = new Scanner(System.in);

            // Taking input
            System.out.print("Enter Movie ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Movie Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Hero: ");
            String hero = sc.nextLine();

            System.out.print("Enter Heroine: ");
            String heroine = sc.nextLine();

            // Using String.format()
            String query = String.format(
                    "INSERT INTO movies VALUES (%d, '%s', '%s', '%s')",
                    id, name, hero, heroine
            );

            // Execute
            int rows = st.executeUpdate(query);

            System.out.println(rows + " row inserted successfully ✅");

            st.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Error ❌");
            e.printStackTrace();
        }
    }
}