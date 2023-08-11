import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MonthlyReport {
    FileReader fileReader = new FileReader();
    LinkedHashMap<String, ArrayList<Transaction>> monthsToTransactions = new LinkedHashMap<>(); /* Не знаю, можно ли было использовать LinkedHashMap.
                                                                                                  Просто метод .keySet() для обычной мапы возвращал ключи не в порядке добавления объектов,
                                                                                                  что приводило к неправильному выводу и ошибке при сверке отчетов, хотя её не должно быть. */

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
    }

    void printStatistic() {
        if (!monthsToTransactions.isEmpty()) {
            System.out.println(String.format("%-8s| %s | %s |", "Месяц", "Максимальный доход", "Максимальная трата"));
            System.out.println("--------------------------------------------------");
            for (String monthName : monthsToTransactions.keySet()) {
                System.out.println(String.format("%-8s|%13d%8s%13d%8s", monthName, maxIncome(monthName), "|", maxExpense(monthName), "|"));
                System.out.println("--------------------------------------------------");
            }
        } else {
            System.out.println("Для вывода статистики необходимо считать отчёты.");
        }
    }

    int maxIncome(String monthName) {
        int maxIncome = 0;
        for (Transaction transaction : monthsToTransactions.get(monthName)) {
            if (!(transaction.is_expense) && ((transaction.unitPrice * transaction.quantity) > maxIncome)) {
                maxIncome = transaction.unitPrice * transaction.quantity;
            }
        }
        return maxIncome;
    }

    int maxExpense(String monthName) {
        int maxExpense = 0;
        for (Transaction transaction : monthsToTransactions.get(monthName)) {
            if ((transaction.is_expense) && ((transaction.unitPrice * transaction.quantity) > maxExpense)) {
                maxExpense = transaction.unitPrice * transaction.quantity;
            }
        }
        return maxExpense;
    }
}

