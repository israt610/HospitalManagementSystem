import java.sql.*;
import java.util.Scanner;

class ProcedureDetailsManager implements CrudOperations {

    @Override
    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Procedure Details ---");
            System.out.println("1. Add Procedure");
            System.out.println("2. View Procedures");
            System.out.println("3. Update Procedure");
            System.out.println("4. Delete Procedure");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addProcedure(connection, scanner);
                    case 2 -> viewProcedures(connection);
                    case 3 -> updateProcedure(connection, scanner);
                    case 4 -> deleteProcedure(connection, scanner);
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

    private void addProcedure(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Procedure Code: ");
        String procedure_code = scanner.nextLine();
        System.out.print("Enter Procedure Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Procedure Cost: ");
        double cost = scanner.nextDouble();

        String query = "INSERT INTO procedureDetails (procedure_code, name, cost) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, procedure_code);
            stmt.setString(2, name);
            stmt.setDouble(3, cost);
            stmt.executeUpdate();
            System.out.println("Procedure added successfully.");
        }
    }

    private void viewProcedures(Connection connection) throws SQLException {
        String query = "SELECT * FROM procedureDetails";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Code: " + rs.getString("procedure_code") + ", Name: " + rs.getString("name") + ", Cost: " + rs.getDouble("cost"));
            }
        }
    }

    private void updateProcedure(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Procedure Code to update: ");
        String procedure_code = scanner.nextLine();
        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Cost: ");
        double cost = scanner.nextDouble();

        String query = "UPDATE procedureDetails SET name = ?, cost = ? WHERE procedure_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDouble(2, cost);
            stmt.setString(3, procedure_code);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Procedure updated successfully.");
            } else {
                System.out.println("Procedure not found.");
            }
        }
    }

    private void deleteProcedure(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Procedure Code to delete: ");
        String procedure_code = scanner.nextLine();

        String query = "DELETE FROM procedureDetails WHERE procedure_code = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, procedure_code);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Procedure deleted successfully.");
            } else {
                System.out.println("Procedure not found.");
            }
        }
    }
}
