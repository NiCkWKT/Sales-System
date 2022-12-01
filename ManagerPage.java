import java.sql.Connection;
import java.sql.SQLException;

public class ManagerPage extends Page {
    public static void start(Connection conn) throws SQLException {
        boolean running = true;
        while (running) {
            printManagerMenu();
            int choice = takeChoiceInput(1, 5);
            switch (choice) {
                case 1:
                    Manager.listAllSalesperson(conn);
                    pressEnterToContinue();
                    break;
                case 2:
                    Manager.countSalesForEachStaff(conn);
                    pressEnterToContinue();
                    break;
                case 3:
                    Manager.sortAndListManufacturer(conn);
                    pressEnterToContinue();
                    break;
                case 4:
                    Manager.NMostPopular(conn);
                    pressEnterToContinue();
                    break;
                default:
                    running = false;
                    break;
            }
        }
    }
}
