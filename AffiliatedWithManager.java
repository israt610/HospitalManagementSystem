import java.sql.*;
import java.util.Scanner;
public class AffiliatedWithManager {
    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Affiliated With Records ---");
            System.out.println("1. Add Affiliation Record");
            System.out.println("2. View Affiliation Records");
            System.out.println("3. Update Affiliation Record");
            System.out.println("4. Delete Affiliation Record");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addAffiliation(connection, scanner);
                    case 2 -> viewAffiliations(connection);
                    case 3 -> updateAffiliation(connection, scanner);
                    case 4 -> deleteAffiliation(connection, scanner);
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

    private void addAffiliation(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Physician ID: ");
        int physicianId = scanner.nextInt();
        System.out.print("Enter Department ID: ");
        int departmentId = scanner.nextInt();
        System.out.print("Is Primary Affiliation (true/false): ");
        boolean primaryAffiliation = scanner.nextBoolean();

        String query = "INSERT INTO affiliated_with (physician_id, department_id, primary_affiliation) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, physicianId);
            stmt.setInt(2, departmentId);
            stmt.setBoolean(3, primaryAffiliation);
            stmt.executeUpdate();
            System.out.println("Affiliation record added successfully.");
        }
    }

    private void viewAffiliations(Connection connection) throws SQLException {
        String query = "SELECT * FROM affiliated_with";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Physician ID: " + rs.getInt("physician_id") + ", Department ID: " + rs.getInt("department_id") + ", Primary Affiliation: " + rs.getBoolean("primary_affiliation"));
            }
        }
    }

    private void updateAffiliation(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Physician ID to update: ");
        int physicianId = scanner.nextInt();
        System.out.print("Enter New Department ID: ");
        int departmentId = scanner.nextInt();
        System.out.print("Is Primary Affiliation (true/false): ");
        boolean primaryAffiliation = scanner.nextBoolean();

        String query = "UPDATE affiliated_with SET department_id = ?, primary_affiliation = ? WHERE physician_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, departmentId);
            stmt.setBoolean(2, primaryAffiliation);
            stmt.setInt(3, physicianId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Affiliation record updated successfully.");
            } else {
                System.out.println("Affiliation record not found.");
            }
        }
    }

    private void deleteAffiliation(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Physician ID to delete: ");
        int physicianId = scanner.nextInt();

        String query = "DELETE FROM affiliated_with WHERE physician_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, physicianId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Affiliation record deleted successfully.");
            } else {
                System.out.println("Affiliation record not found.");
            }
        }
    }
}
