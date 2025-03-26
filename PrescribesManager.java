import java.sql.*;
import java.util.Scanner;

public class PrescribesManager {

    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Prescriptions ---");
            System.out.println("1. Add Prescription");
            System.out.println("2. View Prescriptions");
            System.out.println("3. Update Prescription");
            System.out.println("4. Delete Prescription");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addPrescription(connection, scanner);
                    case 2 -> viewPrescriptions(connection);
                    case 3 -> updatePrescription(connection, scanner);
                    case 4 -> deletePrescription(connection, scanner);
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

    private void addPrescription(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Physician ID: ");
        int physicianId = scanner.nextInt();
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Medication Code: ");
        String medicationCode = scanner.nextLine();
        System.out.print("Enter Prescription Date (YYYY-MM-DD): ");
        String prescriptionDateStr = scanner.nextLine();
        Date prescriptionDate = Date.valueOf(prescriptionDateStr);
        System.out.print("Enter Appointment ID: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Dose: ");
        String dose = scanner.nextLine();

        String query = "INSERT INTO prescribes (physician_id, patient_id, medication_code, prescription_date, appointment_id, dose) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, physicianId);
            stmt.setInt(2, patientId);
            stmt.setString(3, medicationCode);
            stmt.setDate(4, prescriptionDate);
            stmt.setInt(5, appointmentId);
            stmt.setString(6, dose);
            stmt.executeUpdate();
            System.out.println("Prescription added successfully.");
        }
    }

    private void viewPrescriptions(Connection connection) throws SQLException {
        String query = "SELECT * FROM prescribes";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Physician ID: " + rs.getInt("physician_id") +
                        ", Patient ID: " + rs.getInt("patient_id") +
                        ", Medication Code: " + rs.getString("medication_code") +
                        ", Prescription Date: " + rs.getDate("prescription_date") +
                        ", Appointment ID: " + rs.getInt("appointment_id") +
                        ", Dose: " + rs.getString("dose"));
            }
        }
    }

    private void updatePrescription(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Physician ID to update: ");
        int physicianId = scanner.nextInt();
        System.out.print("Enter Patient ID to update: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new Dose: ");
        String newDose = scanner.nextLine();

        String query = "UPDATE prescribes SET dose = ? WHERE physician_id = ? AND patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newDose);
            stmt.setInt(2, physicianId);
            stmt.setInt(3, patientId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Prescription updated successfully.");
            } else {
                System.out.println("Prescription not found.");
            }
        }
    }

    private void deletePrescription(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Physician ID to delete: ");
        int physicianId = scanner.nextInt();
        System.out.print("Enter Patient ID to delete: ");
        int patientId = scanner.nextInt();

        String query = "DELETE FROM prescribes WHERE physician_id = ? AND patient_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, physicianId);
            stmt.setInt(2, patientId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Prescription deleted successfully.");
            } else {
                System.out.println("Prescription not found.");
            }
        }
    }
}

