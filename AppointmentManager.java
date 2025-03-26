import java.sql.*;
import java.util.Scanner;

// AppointmentManager class
public class AppointmentManager {
    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Appointments ---");
            System.out.println("1. Add Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Update Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addAppointment(connection, scanner);
                    case 2 -> viewAppointments(connection);
                    case 3 -> updateAppointment(connection, scanner);
                    case 4 -> deleteAppointment(connection, scanner);
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

    private void addAppointment(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Prep Nurse ID: ");
        int prepNurseId = scanner.nextInt();
        System.out.print("Enter Physician ID: ");
        int physicianId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Start Time (yyyy-mm-dd hh:mm:ss): ");
        Timestamp startTime = Timestamp.valueOf(scanner.nextLine());
        System.out.print("Enter End Time (yyyy-mm-dd hh:mm:ss): ");
        Timestamp endTime = Timestamp.valueOf(scanner.nextLine());
        System.out.print("Enter Examination Room: ");
        String examinationRoom = scanner.nextLine();

        String query = "INSERT INTO appointment (patient_id, prep_nurse_id, physician_id, start_time, end_time, examination_room) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            stmt.setInt(2, prepNurseId);
            stmt.setInt(3, physicianId);
            stmt.setTimestamp(4, startTime);
            stmt.setTimestamp(5, endTime);
            stmt.setString(6, examinationRoom);
            stmt.executeUpdate();
            System.out.println("Appointment added successfully.");
        }
    }

    private void viewAppointments(Connection connection) throws SQLException {
        String query = "SELECT * FROM appointment";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Appointment ID: " + rs.getInt("appointment_id") + ", Patient ID: " + rs.getInt("patient_id") + ", Prep Nurse ID: " + rs.getInt("prep_nurse_id") + ", Physician ID: " + rs.getInt("physician_id") + ", Start Time: " + rs.getTimestamp("start_time") + ", End Time: " + rs.getTimestamp("end_time") + ", Examination Room: " + rs.getString("examination_room"));
            }
        }
    }

    private void updateAppointment(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Appointment ID to update: ");
        int appointmentId = scanner.nextInt();
        System.out.print("Enter New Start Time (yyyy-mm-dd hh:mm:ss): ");
        scanner.nextLine();
        Timestamp startTime = Timestamp.valueOf(scanner.nextLine());
        System.out.print("Enter New End Time (yyyy-mm-dd hh:mm:ss): ");
        Timestamp endTime = Timestamp.valueOf(scanner.nextLine());
        System.out.print("Enter New Examination Room: ");
        String examinationRoom = scanner.nextLine();

        String query = "UPDATE appointment SET start_time = ?, end_time = ?, examination_room = ? WHERE appointment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setTimestamp(1, startTime);
            stmt.setTimestamp(2, endTime);
            stmt.setString(3, examinationRoom);
            stmt.setInt(4, appointmentId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Appointment updated successfully.");
            } else {
                System.out.println("Appointment not found.");
            }
        }
    }

    private void deleteAppointment(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Appointment ID to delete: ");
        int appointmentId = scanner.nextInt();

        String query = "DELETE FROM appointment WHERE appointment_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appointmentId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Appointment deleted successfully.");
            } else {
                System.out.println("Appointment not found.");
            }
        }
    }
}
