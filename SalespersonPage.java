import java.sql.Connection;
import java.sql.SQLException;

public class SalespersonPage extends Page {
    public static void start(Connection conn) throws SQLException {
        boolean running = true;
        while (running) {
            System.out.println();
            printSalesMenu();
            int choice = takeChoiceInput(1, 3);
            switch (choice) {
                case 1:
                    Salesperson.searchForPart(conn);
                    pressEnterToContinue();
                    break;
                case 2:
                    Salesperson.sellPart(conn);
                    System.out.println();
                    pressEnterToContinue();
                    break;
                default:
                    running = false;
                    break;
            }
        }
    }
}
