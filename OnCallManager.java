import java.sql.*;
import java.util.Scanner;
public class OnCallManager {
    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage On-Call Assignments ---");
            System.out.println("1. Add On-Call Assignment");
            System.out.println("2. View On-Call Assignments");
            System.out.println("3. Update On-Call Assignment");
            System.out.println("4. Delete On-Call Assignment");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addOnCall(connection, scanner);
                    case 2 -> viewOnCalls(connection);
                    case 3 -> updateOnCall(connection, scanner);
                    case 4 -> deleteOnCall(connection, scanner);
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

    private void addOnCall(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Nurse ID: ");
        int nurseId = scanner.nextInt();
        System.out.print("Enter Block Floor: ");
        int blockFloor = scanner.nextInt();
        System.out.print("Enter Block Code: ");
        int blockCode = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter On-Call Start Time (yyyy-mm-dd hh:mm:ss): ");
        Timestamp onCallStart = Timestamp.valueOf(scanner.nextLine());
        System.out.print("Enter On-Call End Time (yyyy-mm-dd hh:mm:ss): ");
        Timestamp onCallEnd = Timestamp.valueOf(scanner.nextLine());

        String query = "INSERT INTO on_call (nurse_id, blockfloor, blockcode, oncall_start, oncall_end) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, nurseId);
            stmt.setInt(2, blockFloor);
            stmt.setInt(3, blockCode);
            stmt.setTimestamp(4, onCallStart);
            stmt.setTimestamp(5, onCallEnd);
            stmt.executeUpdate();
            System.out.println("On-Call Assignment added successfully.");
        }
    }

    private void viewOnCalls(Connection connection) throws SQLException {
        String query = "SELECT * FROM on_call";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Nurse ID: " + rs.getInt("nurse_id") + ", Block Floor: " + rs.getInt("blockfloor") + ", Block Code: " + rs.getInt("blockcode") + ", Start Time: " + rs.getTimestamp("oncall_start") + ", End Time: " + rs.getTimestamp("oncall_end"));
            }
        }
    }

    private void updateOnCall(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Nurse ID to update On-Call Assignment: ");
        int nurseId = scanner.nextInt();
        System.out.print("Enter New On-Call Start Time (yyyy-mm-dd hh:mm:ss): ");
        scanner.nextLine();
        Timestamp onCallStart = Timestamp.valueOf(scanner.nextLine());
        System.out.print("Enter New On-Call End Time (yyyy-mm-dd hh:mm:ss): ");
        Timestamp onCallEnd = Timestamp.valueOf(scanner.nextLine());

        String query = "UPDATE on_call SET oncall_start = ?, oncall_end = ? WHERE nurse_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setTimestamp(1, onCallStart);
            stmt.setTimestamp(2, onCallEnd);
            stmt.setInt(3, nurseId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("On-Call Assignment updated successfully.");
            } else {
                System.out.println("On-Call Assignment not found.");
            }
        }
    }

    private void deleteOnCall(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Nurse ID to delete On-Call Assignment: ");
        int nurseId = scanner.nextInt();

        String query = "DELETE FROM on_call WHERE nurse_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, nurseId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("On-Call Assignment deleted successfully.");
            } else {
                System.out.println("On-Call Assignment not found.");
            }
        }
    }
}

