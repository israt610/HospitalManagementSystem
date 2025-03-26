import java.sql.*;
import java.util.Scanner;

interface CrudOperations {
    void manage(Connection connection, Scanner scanner);
}
