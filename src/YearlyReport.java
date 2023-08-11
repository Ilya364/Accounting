import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    int year = 0;
    FileReader fileReader = new FileReader();
    ArrayList<MonthSummary> monthSummaries = new ArrayList<>();

    void addYearlyReport(int year, String fileName) {
        this.year = year;
        ArrayList<String> lines = fileReader.readFileContents(fileName);
        for (int i = 1; i < lines.size(); i++) {
            String[] columns = lines.get(i).split(",");

            int month = Integer.parseInt(columns[0]);
            int amount = Integer.parseInt(columns[1]);
            boolean isExpense = Boolean.parseBoolean(columns[2]);

            monthSummaries.add(new MonthSummary(month, amount, isExpense));
        }
    }

    void printStatistic() {
        if (!monthSummaries.isEmpty()) {
            System.out.println(String.format("%-4s | %s | %s | %s |", "Год", "Прибыль по месяцам", "Средний расход по операциям", "Средний доход по операциям"));
            System.out.println("--------------------------------------------------------------------------------------");

            System.out.print(year);
            ArrayList<String> lines = new ArrayList<>();
            HashMap<Integer, Integer> monthsProfits = getProfits();

            for (Integer month : monthsProfits.keySet())
                lines.add(String.format(" | 0" + month + ": %-14d |", monthsProfits.get(month)));

            lines.set(0, lines.get(0).concat(String.format("%19.1f %10s %18.1f %9s", averageExpense(), "|", averageIncome(), "|")));
            lines.set(1, "    ".concat(lines.get(1)).concat("                             |                            |"));
            lines.set(2, "    ".concat(lines.get(2)).concat("                             |                            |"));

            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println("--------------------------------------------------------------------------------------");
        } else {
            System.out.println("Для вывода статистики необходимо считать отчёты.");
        }
    }

    double averageIncome() {
        int sum = 0;
        int months = 0;
        for (MonthSummary monthSummary : monthSummaries) {
            if (!monthSummary.is_expense) {
                sum += monthSummary.amount;
                months++;
            }
        }
        double averageIncome = (double) sum / months;
        return averageIncome;
    }

    double averageExpense() {
        int sum = 0;
        int months = 0;
        for (MonthSummary monthSummary : monthSummaries) {
            if (monthSummary.is_expense) {
                sum += monthSummary.amount;
                months++;
            }
        }
        double averageExpense = (double) sum / months;
        return averageExpense;
    }

    HashMap<Integer, Integer> getProfits() {
        HashMap<Integer, Integer> profitsFromMonths = new HashMap<>();

        for (MonthSummary monthSummary : monthSummaries) {
            if (!monthSummary.is_expense) {
                profitsFromMonths.put(monthSummary.month, profitsFromMonths.getOrDefault(monthSummary.month, 0) + monthSummary.amount);
            } else {
                profitsFromMonths.put(monthSummary.month, profitsFromMonths.getOrDefault(monthSummary.month, 0) - monthSummary.amount);
            }
        }
        return profitsFromMonths;
    }
}
