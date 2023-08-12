import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    FileReader fileReader = new FileReader();
    HashMap<String, ArrayList<Transaction>> monthsToTransactions = new HashMap<>();
    ArrayList<String> months = new ArrayList<>();

    void addMonthlyReport(String monthName, String fileName) {
        ArrayList<String> lines = fileReader.readFileContents(fileName);
        ArrayList<Transaction> transactions = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] columns = lines.get(i).split(",");

            String name = columns[0];
            boolean is_expense = Boolean.parseBoolean(columns[1]);
            int quantity = Integer.parseInt(columns[2]);
            int unitPrice = Integer.parseInt(columns[3]);

            transactions.add(new Transaction(name, is_expense, quantity, unitPrice));
        }
        monthsToTransactions.put(monthName, transactions);
        months.add(monthName);
    }

    void printStatistic() {
        if (!monthsToTransactions.isEmpty()) {
            System.out.println(String.format("%-8s| %s | %s |", "Месяц", "Максимальный доход", "Максимальная трата"));
            System.out.println("--------------------------------------------------");
            boolean isExpense = true;
            for (String monthName : months) {
                System.out.println(String.format("%-8s|%13d%8s%13d%8s", monthName, maxIncomeOrExpense(monthName, !isExpense), "|", maxIncomeOrExpense(monthName, isExpense), "|"));
                System.out.println("--------------------------------------------------");
            }
        } else {
            System.out.println("Для вывода статистики необходимо считать отчёты.");
        }
    }

    int maxIncomeOrExpense(String monthName, boolean isExpense) {
        int max = 0;
        int currentAmount = 0;
        for (Transaction transaction : monthsToTransactions.get(monthName)) {
            currentAmount = transaction.unitPrice * transaction.quantity;
            if ((!isExpense) && !(transaction.is_expense) && (currentAmount > max)) {
                max = transaction.unitPrice * transaction.quantity;
            } else if ((isExpense) && (transaction.is_expense) && (currentAmount > max)) {
                max = transaction.unitPrice * transaction.quantity;
            }
        }
        return max;
    }
}

