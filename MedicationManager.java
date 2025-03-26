import java.sql.*;
import java.util.Scanner;
public class MedicationManager {
    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Medications ---");
            System.out.println("1. Add Medication");
            System.out.println("2. View Medications");
            System.out.println("3. Update Medication");
            System.out.println("4. Delete Medication");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addMedication(connection, scanner);
                    case 2 -> viewMedications(connection);
                    case 3 -> updateMedication(connection, scanner);
                    case 4 -> deleteMedication(connection, scanner);
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

    private void addMedication(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Medication Code: ");
        String medicationCode = scanner.nextLine();
        System.out.print("Enter Medication Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Medication Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter Medication Description: ");
        String description = scanner.nextLine();

        String query = "INSERT INTO medication (medication_code, name, brand, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, medicationCode);
            stmt.setString(2, name);
            stmt.setString(3, brand);
            stmt.setString(4, description);
            stmt.executeUpdate();
            System.out.println("Medication added successfully.");
        }
    }

    private void viewMedications(Connection connection) throws SQLException {
        String query = "SELECT * FROM medication";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Medication Code: " + rs.getString("medication_code") + ", Name: " + rs.getString("name") + ", Brand: " + rs.getString("brand") + ", Description: " + rs.getString("description"));
            }
        }
    }

    private void updateMedication(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Medication Code to update: ");
        String medicationCode = scanner.nextLine();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter New Description: ");
        String description = scanner.nextLine();

        String query = "UPDATE medication SET name = ?, brand = ?, description = ? WHERE medication_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, brand);
            stmt.setString(3, description);
            stmt.setString(4, medicationCode);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Medication updated successfully.");
            } else {
                System.out.println("Medication not found.");
            }
        }
    }

    private void deleteMedication(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Medication Code to delete: ");
        String medicationCode = scanner.nextLine();

        String query = "DELETE FROM medication WHERE medication_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, medicationCode);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Medication deleted successfully.");
            } else {
                System.out.println("Medication not found.");
            }
        }
    }
}
