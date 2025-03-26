import java.sql.*;
import java.util.Scanner;

public class PatientManager {

    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Patients ---");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addPatient(connection, scanner);
                    case 2 -> viewPatients(connection);
                    case 3 -> updatePatient(connection, scanner);
                    case 4 -> deletePatient(connection, scanner);
                    case 5 -> exit = true;
                    default -> System.out.println("Invalid option.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addPatient(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Insurance ID: ");
        String insuranceId = scanner.nextLine();
        System.out.print("Enter Primary Care Physician ID: ");
        int primaryCarePhysician = scanner.nextInt();
        scanner.nextLine();

        String query = "INSERT INTO patient (name, address, phone, insurance_id, primary_care_physician) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, phone);
            stmt.setString(4, insuranceId);
            stmt.setInt(5, primaryCarePhysician);
            stmt.executeUpdate();
            System.out.println("Patient added successfully.");
        }
    }

    private void viewPatients(Connection connection) throws SQLException {
        String query = "SELECT * FROM patient";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("social_security_number") + ", Name: " + rs.getString("name") + ", Address: " + rs.getString("address") + ", Phone: " + rs.getString("phone") + ", Insurance ID: " + rs.getString("insurance_id") + ", Primary Care Physician: " + rs.getInt("primary_care_physician"));
            }
        }
    }

    private void updatePatient(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Patient ID to update: ");
        int social_security_number = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter new Phone: ");
        String phone = scanner.nextLine();

        String query = "UPDATE patient SET address = ?, phone = ? WHERE social_security_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, address);
            stmt.setString(2, phone);
            stmt.setInt(3, social_security_number);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Patient updated successfully.");
            } else {
                System.out.println("Patient not found.");
            }
        }
    }

    private void deletePatient(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Patient ID to delete: ");
        int social_security_number = scanner.nextInt();

        String query = "DELETE FROM patient WHERE social_security_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, social_security_number);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Patient deleted successfully.");
            } else {
                System.out.println("Patient not found.");
            }
        }
    }
}
