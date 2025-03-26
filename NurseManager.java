import java.sql.*;
import java.util.Scanner;
public class NurseManager {
    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Nurses ---");
            System.out.println("1. Add Nurse");
            System.out.println("2. View Nurses");
            System.out.println("3. Update Nurse");
            System.out.println("4. Delete Nurse");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addNurse(connection, scanner);
                    case 2 -> viewNurses(connection);
                    case 3 -> updateNurse(connection, scanner);
                    case 4 -> deleteNurse(connection, scanner);
                    case 5 -> {
                        System.out.println("Returning to Main Menu...");
                        exit = true;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addNurse(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Nurse Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Position: ");
        String position = scanner.nextLine();
        System.out.print("Is Registered (true/false): ");
        boolean registered = scanner.nextBoolean();
        System.out.print("Enter Social Security Number: ");
        String ssn = scanner.next();

        String query = "INSERT INTO nurse (name, position, registered, social_security_number) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, position);
            stmt.setBoolean(3, registered);
            stmt.setString(4, ssn);
            stmt.executeUpdate();
            System.out.println("Nurse added successfully.");
        }
    }

    private void viewNurses(Connection connection) throws SQLException {
        String query = "SELECT * FROM nurse";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("employee_id") + ", Name: " + rs.getString("name") + ", Position: " + rs.getString("position") + ", Registered: " + rs.getBoolean("registered") + ", SSN: " + rs.getString("social_security_number"));
            }
        }
    }

    private void updateNurse(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Nurse ID to update: ");
        int nurseId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Position: ");
        String position = scanner.nextLine();
        System.out.print("Is Registered (true/false): ");
        boolean registered = scanner.nextBoolean();

        String query = "UPDATE nurse SET position = ?, registered = ? WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, position);
            stmt.setBoolean(2, registered);
            stmt.setInt(3, nurseId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Nurse updated successfully.");
            } else {
                System.out.println("Nurse not found.");
            }
        }
    }

    private void deleteNurse(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Nurse ID to delete: ");
        int nurseId = scanner.nextInt();

        String query = "DELETE FROM nurse WHERE employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, nurseId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Nurse deleted successfully.");
            } else {
                System.out.println("Nurse not found.");
            }
        }
    }
}
