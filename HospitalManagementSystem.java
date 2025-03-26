import java.sql.Connection;
import java.util.Scanner;

public class HospitalManagementSystem {

    public static void main(String[] args) {
        HospitalManagementSystem system = new HospitalManagementSystem();
        system.run();
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in); Connection connection = DatabaseConnection.connect()) {
            System.out.println();
            System.out.println();

            System.out.println("_______________Welcome to Hospital Management System!_______________");

            while (true) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> new ProcedureDetailsManager().manage(connection, scanner);
                    case 2-> new BlockManager().manage(connection, scanner);
                    case 3-> new RoomManager().manage(connection, scanner);
                    case 4-> new PhysicianManager().manage(connection, scanner);
                    case 5-> new DepartmentManager().manage(connection, scanner);
                    case 6-> new MedicationManager().manage(connection, scanner);
                    case 7 -> new AffiliatedWithManager().manage(connection, scanner);
                    case 8 -> new PatientManager().manage(connection, scanner);
                    case 9 -> new StayManager().manage(connection, scanner);
                    case 10-> new TrainedManager().manage(connection, scanner);
                    case 11 -> new NurseManager().manage(connection, scanner);
                    case 12 -> new UndergoesManager().manage(connection, scanner);
                    case 13 -> new AppointmentManager().manage(connection, scanner);
                    case 14 -> new PrescribesManager().manage(connection, scanner);
                    case 15 -> new OnCallManager().manage(connection, scanner);
                    case 16 -> {
                        System.out.println("Exiting system. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Manage Procedure Details");
        System.out.println("2. Manage Blocks");
        System.out.println("3. Manage Rooms");
        System.out.println("4. Manage Physicians");
        System.out.println("5. Manage Departments");
        System.out.println("6. Manage Medications");
        System.out.println("7. Manage Affiliated With");
        System.out.println("8. Manage Patients");
        System.out.println("9. Manage Stays");
        System.out.println("10. Manage Trained_In");
        System.out.println("11. Manage Nurses");
        System.out.println("12. Manage Undergoes Procedures");
        System.out.println("13. Manage Appointments");
        System.out.println("14. Manage Prescriptions");
        System.out.println("15. Manage On-Call Schedules");
        System.out.println("16. Exit");
        System.out.print("Choose an option: ");
    }
}
