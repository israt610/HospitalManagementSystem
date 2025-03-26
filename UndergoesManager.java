import java.sql.*;
import java.util.Scanner;

public class UndergoesManager {
    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Undergoes Records ---");
            System.out.println("1. Add Undergoes Record");
            System.out.println("2. View Undergoes Records");
            System.out.println("3. Update Undergoes Record");
            System.out.println("4. Delete Undergoes Record");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addUndergoes(connection, scanner);
                    case 2 -> viewUndergoes(connection);
                    case 3 -> updateUndergoes(connection, scanner);
                    case 4 -> deleteUndergoes(connection, scanner);
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

    private void addUndergoes(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Procedure Details ID: ");
        int procedureDetails = scanner.nextInt();
        System.out.print("Enter Stay ID: ");
        int stayId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Date (yyyy-mm-dd): ");
        Date date = Date.valueOf(scanner.nextLine());
        System.out.print("Enter Physician ID: ");
        int physicianId = scanner.nextInt();
        System.out.print("Enter Assisting Nurse ID: ");
        int assistingNurseId = scanner.nextInt();

        String query = "INSERT INTO undergoes (patient_id, procedure_details, stay_id, date_, physician_id, assisting_nurse_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            stmt.setInt(2, procedureDetails);
            stmt.setInt(3, stayId);
            stmt.setDate(4, date);
            stmt.setInt(5, physicianId);
            stmt.setInt(6, assistingNurseId);
            stmt.executeUpdate();
            System.out.println("Undergoes record added successfully.");
        }
    }

    private void viewUndergoes(Connection connection) throws SQLException {
        String query = "SELECT * FROM undergoes";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Patient ID: " + rs.getInt("patient_id") + ", Procedure Details: " + rs.getInt("procedure_details") + ", Stay ID: " + rs.getInt("stay_id") + ", Date: " + rs.getDate("date_") + ", Physician ID: " + rs.getInt("physician_id") + ", Assisting Nurse ID: " + rs.getInt("assisting_nurse_id"));
            }
        }
    }

    private void updateUndergoes(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Patient ID to update: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter New Procedure Details ID: ");
        int procedureDetails = scanner.nextInt();
        System.out.print("Enter New Stay ID: ");
        int stayId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Date (yyyy-mm-dd): ");
        Date date = Date.valueOf(scanner.nextLine());
        System.out.print("Enter New Physician ID: ");
        int physicianId = scanner.nextInt();
        System.out.print("Enter New Assisting Nurse ID: ");
        int assistingNurseId = scanner.nextInt();

        String query = "UPDATE undergoes SET procedure_details = ?, stay_id = ?, date_ = ?, physician_id = ?, assisting_nurse_id = ? WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, procedureDetails);
            stmt.setInt(2, stayId);
            stmt.setDate(3, date);
            stmt.setInt(4, physicianId);
            stmt.setInt(5, assistingNurseId);
            stmt.setInt(6, patientId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Undergoes record updated successfully.");
            } else {
                System.out.println("Undergoes record not found.");
            }
        }
    }

    private void deleteUndergoes(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Patient ID to delete: ");
        int patientId = scanner.nextInt();

        String query = "DELETE FROM undergoes WHERE patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Undergoes record deleted successfully.");
            } else {
                System.out.println("Undergoes record not found.");
            }
        }
    }
}