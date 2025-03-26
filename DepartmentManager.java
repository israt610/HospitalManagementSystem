import java.sql.*;
import java.util.Scanner;
public class DepartmentManager {
    public void manage(Connection connection, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Manage Departments ---");
            System.out.println("1. Add Department");
            System.out.println("2. View Departments");
            System.out.println("3. Update Department");
            System.out.println("4. Delete Department");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addDepartment(connection, scanner);
                    case 2 -> viewDepartments(connection);
                    case 3 -> updateDepartment(connection, scanner);
                    case 4 -> deleteDepartment(connection, scanner);
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

    private void addDepartment(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Department Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Department Head: ");
        String head = scanner.nextLine();

        String query = "INSERT INTO department (name, head) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, head);
            stmt.executeUpdate();
            System.out.println("Department added successfully.");
        }
    }

    private void viewDepartments(Connection connection) throws SQLException {
        String query = "SELECT * FROM department";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Department ID: " + rs.getInt("department_id") + ", Name: " + rs.getString("name") + ", Head: " + rs.getString("head"));
            }
        }
    }

    private void updateDepartment(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Department ID to update: ");
        int departmentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Department Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Department Head: ");
        String head = scanner.nextLine();

        String query = "UPDATE department SET name = ?, head = ? WHERE department_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, head);
            stmt.setInt(3, departmentId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Department updated successfully.");
            } else {
                System.out.println("Department not found.");
            }
        }
    }

    private void deleteDepartment(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Department ID to delete: ");
        int departmentId = scanner.nextInt();

        String query = "DELETE FROM department WHERE department_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, departmentId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Department deleted successfully.");
            } else {
                System.out.println("Department not found.");
            }
        }
    }
}
