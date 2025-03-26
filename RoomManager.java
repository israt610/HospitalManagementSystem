import java.sql.*;
import java.util.Scanner;

public class RoomManager {
    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Rooms ---");
            System.out.println("1. Add Room");
            System.out.println("2. View Rooms");
            System.out.println("3. Update Room");
            System.out.println("4. Delete Room");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addRoom(connection, scanner);
                    case 2 -> viewRooms(connection);
                    case 3 -> updateRoom(connection, scanner);
                    case 4 -> deleteRoom(connection, scanner);
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

    private void addRoom(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Room Number: ");
        int room_number = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Room Type: ");
        String roomtype = scanner.nextLine();
        System.out.print("Enter Block Floor: ");
        String blockfloor = scanner.nextLine();
        System.out.print("Enter Block Code: ");
        String blockcode = scanner.nextLine();
        System.out.print("Is the room unavailable (true/false): ");
        boolean unavailable = scanner.nextBoolean();

        String query = "INSERT INTO room (room_number, roomtype, blockfloor, blockcode, unavailable) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, room_number);
            stmt.setString(2, roomtype);
            stmt.setString(3, blockfloor);
            stmt.setString(4, blockcode);
            stmt.setBoolean(5, unavailable);
            stmt.executeUpdate();
            System.out.println("Room added successfully.");
        }
    }

    private void viewRooms(Connection connection) throws SQLException {
        String query = "SELECT * FROM room";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Room Number: " + rs.getInt("room_number") + ", Room Type: " + rs.getString("roomtype") +
                        ", Block Floor: " + rs.getString("blockfloor") + ", Block Code: " + rs.getString("blockcode") +
                        ", Unavailable: " + rs.getBoolean("unavailable"));
            }
        }
    }

    private void updateRoom(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Room Number to update: ");
        int room_number = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new Room Type: ");
        String roomtype = scanner.nextLine();
        System.out.print("Enter new Block Floor: ");
        String blockfloor = scanner.nextLine();
        System.out.print("Enter new Block Code: ");
        String blockcode = scanner.nextLine();
        System.out.print("Is the room unavailable (true/false): ");
        boolean unavailable = scanner.nextBoolean();

        String query = "UPDATE room SET roomtype = ?, blockfloor = ?, blockcode = ?, unavailable = ? WHERE room_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, roomtype);
            stmt.setString(2, blockfloor);
            stmt.setString(3, blockcode);
            stmt.setBoolean(4, unavailable);
            stmt.setInt(5, room_number);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Room updated successfully.");
            } else {
                System.out.println("Room not found.");
            }
        }
    }

    private void deleteRoom(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Room Number to delete: ");
        int room_number = scanner.nextInt();

        String query = "DELETE FROM room WHERE room_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, room_number);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Room deleted successfully.");
            } else {
                System.out.println("Room not found.");
            }
        }
    }
}
