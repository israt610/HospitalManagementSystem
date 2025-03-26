import java.sql.*;
import java.util.Scanner;
public class TrainedManager {
    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Trained Records ---");
            System.out.println("1. Add Trained Record");
            System.out.println("2. View Trained Records");
            System.out.println("3. Update Trained Record");
            System.out.println("4. Delete Trained Record");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addTrained(connection, scanner);
                    case 2 -> viewTrained(connection);
                    case 3 -> updateTrained(connection, scanner);
                    case 4 -> deleteTrained(connection, scanner);
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

    private void addTrained(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Physician ID: ");
        int physicianId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Treatment: ");
        String treatment = scanner.nextLine();
        System.out.print("Enter Certification Date (yyyy-mm-dd): ");
        Date certificationDate = Date.valueOf(scanner.nextLine());
        System.out.print("Enter Certification Expiry Date (yyyy-mm-dd): ");
        Date certificationExpires = Date.valueOf(scanner.nextLine());

        String query = "INSERT INTO trained_in (physician_id, treatment, certification_date, certification_expires) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, physicianId);
            stmt.setString(2, treatment);
            stmt.setDate(3, certificationDate);
            stmt.setDate(4, certificationExpires);
            stmt.executeUpdate();
            System.out.println("Trained record added successfully.");
        }
    }

    private void viewTrained(Connection connection) throws SQLException {
        String query = "SELECT * FROM trained_in";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Physician ID: " + rs.getInt("physician_id") + ", Treatment: " + rs.getString("treatment") + ", Certification Date: " + rs.getDate("certification_date") + ", Expiry Date: " + rs.getDate("certification_expires"));
            }
        }
    }

    private void updateTrained(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Physician ID to update: ");
        int physicianId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Treatment: ");
        String treatment = scanner.nextLine();
        System.out.print("Enter New Certification Expiry Date (yyyy-mm-dd): ");
        Date certificationExpires = Date.valueOf(scanner.nextLine());

        String query = "UPDATE trained_in SET treatment = ?, certification_expires = ? WHERE physician_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, treatment);
            stmt.setDate(2, certificationExpires);
            stmt.setInt(3, physicianId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Trained record updated successfully.");
            } else {
                System.out.println("Trained record not found.");
            }
        }
    }

    private void deleteTrained(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Physician ID to delete: ");
        int physicianId = scanner.nextInt();

        String query = "DELETE FROM trained_in WHERE physician_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, physicianId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Trained record deleted successfully.");
            } else {
                System.out.println("Trained record not found.");
            }
        }
    }
}

