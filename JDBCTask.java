package jdbcConnectionProgram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTask {

	public static void main(String[] args) {
	
		// Database credentials
        String url = "jdbc:mysql://localhost:3306/myemployee"; 
        String username = "root"; 
        String password = "root"; 

        // SQL Insert Query
        String insertQuery = "INSERT INTO myemployee (empcode, empname, empage, esalary) VALUES (?, ?, ?, ?)";
        
        // SQL Select Query to print the data
        String selectQuery = "SELECT * FROM myemployee";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Create a prepared statement for inserting data
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                // Inserting data
                insertStmt.setInt(1, 101);
                insertStmt.setString(2, "Jenny");
                insertStmt.setInt(3, 25);
                insertStmt.setDouble(4, 10000);
                insertStmt.executeUpdate();

                insertStmt.setInt(1, 102);
                insertStmt.setString(2, "Jacky");
                insertStmt.setInt(3, 30);
                insertStmt.setDouble(4, 20000);
                insertStmt.executeUpdate();

                insertStmt.setInt(1, 103);
                insertStmt.setString(2, "Joe");
                insertStmt.setInt(3, 20);
                insertStmt.setDouble(4, 40000);
                insertStmt.executeUpdate();

                insertStmt.setInt(1, 104);
                insertStmt.setString(2, "John");
                insertStmt.setInt(3, 40);
                insertStmt.setDouble(4, 80000);
                insertStmt.executeUpdate();

                insertStmt.setInt(1, 105);
                insertStmt.setString(2, "Shameer");
                insertStmt.setInt(3, 25);
                insertStmt.setDouble(4, 90000);
                insertStmt.executeUpdate();
                
                System.out.println("Data inserted successfully.\n");
            }

            // Create a statement to fetch and display the data
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectQuery)) {
                // Printing the output
                System.out.printf("%-10s %-20s %-10s %-10s\n", "empcode", "empname", "empage", "esalary");
                
                while (rs.next()) {
                    int empcode = rs.getInt("empcode");
                    String empname = rs.getString("empname");
                    int empage = rs.getInt("empage");
                    double esalary = rs.getDouble("esalary");

                    System.out.printf("%-10d %-20s %-10d %-10.2f\n", empcode, empname, empage, esalary);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

}

/*

OUTPUT:

Data inserted successfully.

empcode    empname              empage     esalary   
101        Jenny                25         10000.00  
102        Jacky                30         20000.00  
103        Joe                  20         40000.00  
104        John                 40         80000.00  
105        Shameer              25         90000.00 

*/


