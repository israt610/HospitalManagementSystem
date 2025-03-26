import java.sql.*;
import java.util.Scanner;

class BlockManager implements CrudOperations {

    @Override
    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Blocks ---");
            System.out.println("1. Add Block");
            System.out.println("2. View Blocks");
            System.out.println("3. Update Block");
            System.out.println("4. Delete Block");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addBlock(connection, scanner);
                    case 2 -> viewBlocks(connection);
                    case 3 -> updateBlock(connection, scanner);
                    case 4 -> deleteBlock(connection, scanner);
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

    private void addBlock(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Block Floor: ");
        String blockfloor = scanner.nextLine();
        System.out.print("Enter Block Code: ");
        String blockcode = scanner.nextLine();

        String query = "INSERT INTO block (blockfloor, blockcode) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, blockfloor);
            stmt.setString(2, blockcode);
            stmt.executeUpdate();
            System.out.println("Block added successfully.");
        }
    }

    private void viewBlocks(Connection connection) throws SQLException {
        String query = "SELECT * FROM block";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Floor: " + rs.getString("blockfloor") + ", Code: " + rs.getString("blockcode"));
            }
        }
    }

    private void updateBlock(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Block Code to update: ");
        String blockcode = scanner.nextLine();
        System.out.print("Enter new Floor: ");
        String blockfloor = scanner.nextLine();

        String query = "UPDATE block SET blockfloor = ? WHERE blockcode = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, blockfloor);
            stmt.setString(2, blockcode);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Block updated successfully.");
            } else {
                System.out.println("Block not found.");
            }
        }
    }

    private void deleteBlock(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Block Code to delete: ");
        String blockcode = scanner.nextLine();

        String query = "DELETE FROM block WHERE blockcode = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, blockcode);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Block deleted successfully.");
            } else {
                System.out.println("Block not found.");
            }
        }
    }
}
