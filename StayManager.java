import java.sql.*;
import java.util.Scanner;

public class StayManager {

    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Stays ---");
            System.out.println("1. Add Stay");
            System.out.println("2. View Stays");
            System.out.println("3. Update Stay");
            System.out.println("4. Delete Stay");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addStay(connection, scanner);
                    case 2 -> viewStays(connection);
                    case 3 -> updateStay(connection, scanner);
                    case 4 -> deleteStay(connection, scanner);
                    case 5 -> {
                        System.out.println("Returning to Main Menu...");
                        exit = true;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addStay(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Room ID: ");
        int roomId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Start Time (YYYY-MM-DD HH:MM:SS): ");
        String startTimeStr = scanner.nextLine();
        System.out.print("Enter End Time (YYYY-MM-DD HH:MM:SS): ");
        String endTimeStr = scanner.nextLine();

        Timestamp startTime = Timestamp.valueOf(startTimeStr);
        Timestamp endTime = Timestamp.valueOf(endTimeStr);

        String query = "INSERT INTO stay(patient_id, room_id, start_time, end_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            stmt.setInt(2, roomId);
            stmt.setTimestamp(3, startTime);
            stmt.setTimestamp(4, endTime);
            stmt.executeUpdate();
            System.out.println("Stay added successfully.");
        }
    }

    private void viewStays(Connection connection) throws SQLException {
        String query = "SELECT * FROM stay";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int stayId = rs.getInt("stay_id");
                int patientId = rs.getInt("patient_id");
                int roomId = rs.getInt("room_id");
                Timestamp startTime = rs.getTimestamp("start_time");
                Timestamp endTime = rs.getTimestamp("end_time");

                System.out.println("Stay ID: " + stayId + ", Patient ID: " + patientId + ", Room ID: " + roomId + ", Start Time: " + startTime + ", End Time: " + endTime);
            }
        }
    }

    private void updateStay(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Stay ID to update: ");
        int stayId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new Room ID: ");
        int roomId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new Start Time (YYYY-MM-DD HH:MM:SS): ");
        String startTimeStr = scanner.nextLine();
        System.out.print("Enter new End Time (YYYY-MM-DD HH:MM:SS): ");
        String endTimeStr = scanner.nextLine();

        Timestamp startTime = Timestamp.valueOf(startTimeStr);
        Timestamp endTime = Timestamp.valueOf(endTimeStr);

        String query = "UPDATE stay SET room_id = ?, start_time = ?, end_time = ? WHERE stay_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, roomId);
            stmt.setTimestamp(2, startTime);
            stmt.setTimestamp(3, endTime);
            stmt.setInt(4, stayId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Stay updated successfully.");
            } else {
                System.out.println("Stay not found.");
            }
        }
    }

    private void deleteStay(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Stay ID to delete: ");
        int stayId = scanner.nextInt();

        String query = "DELETE FROM stay WHERE stay_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, stayId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Stay deleted successfully.");
            } else {
                System.out.println("Stay not found.");
            }
        }
    }
}