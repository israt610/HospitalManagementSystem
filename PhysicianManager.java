import java.sql.*;
import java.util.Scanner;
public class PhysicianManager {

    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Physicians ---");
            System.out.println("1. Add Physician");
            System.out.println("2. View Physicians");
            System.out.println("3. Update Physician");
            System.out.println("4. Delete Physician");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addPhysician(connection, scanner);
                    case 2 -> viewPhysicians(connection);
                    case 3 -> updatePhysician(connection, scanner);
                    case 4 -> deletePhysician(connection, scanner);
                    case 5 -> exit = true;
                    default -> System.out.println("Invalid option.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addPhysician(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Physician Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Position: ");
        String position = scanner.nextLine();
        System.out.print("Enter Social Security Number: ");
        String Social_security_number = scanner.nextLine();

        String query = "INSERT INTO physician (name, position, social_security_number) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setString(3, Social_security_number);
            stmt.executeUpdate();
            System.out.println("Physician added successfully.");
        }
    }

    private void viewPhysicians(Connection connection) throws SQLException {
        String query = "SELECT * FROM physician";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("employee_id") + ", Name: " + rs.getString("name") + ", Position: " + rs.getString("position") + ", SSN: " + rs.getString("social_security_number"));
            }
        }
    }

    private void updatePhysician(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Physician ID to update: ");
        int employee_id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new Position: ");
        String position = scanner.nextLine();

        String query = "UPDATE physician SET position = ? WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, position);
            stmt.setInt(2, employee_id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Physician updated successfully.");
            } else {
                System.out.println("Physician not found.");
            }
        }
    }

    private void deletePhysician(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Physician ID to delete: ");
        int employee_id = scanner.nextInt();

        String query = "DELETE FROM physician WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, employee_id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Physician deleted successfully.");
            } else {
                System.out.println("Physician not found.");
            }
        }
    }
}
