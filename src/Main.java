import java.util.Scanner;

public class Main {
    static final int numberOfMonthlyReports = 3;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport2021 = new YearlyReport();
        Checker checker = new Checker();

        String[] months = {"Январь", "Февраль", "Март"};

        while (true) {
            printMenu();
            Commands command = Commands.values()[scanner.nextInt()];
            switch (command) {
                case READ_MONTHS:
                    for (int month = 1; month <= numberOfMonthlyReports; month++) {
                        monthlyReport.addMonthlyReport(months[month - 1], "m.20210" + month + ".csv");
                    }
                    if (!monthlyReport.monthsToTransactions.isEmpty())
                        System.out.println("Месячные отчеты успешно считаны.");
                    break;
                case READ_YEAR:
                    yearlyReport2021.addYearlyReport(months, 2021, "y.2021.csv");
                    if (!yearlyReport2021.monthSummaries.isEmpty())
                        System.out.println("Годовой отчет успешно считан.");
                    break;
                case CHECK_REPORTS:
                    checker.check(monthlyReport, yearlyReport2021);
                    break;
                case MONTH_STATISTIC:
                    monthlyReport.printStatistic();
                    break;
                case YEAR_STATISTIC:
                    yearlyReport2021.printStatistic();
                    break;
                case END:
                    System.out.println("Работа завершена.");
                    return;
                default:
                    System.out.println("Такой команды нет.");
                    break;
            }
        }
    }

    static void printMenu() {
        System.out.println("1.Считать все месячные отчеты" + System.lineSeparator() +
                "2.Считать годовой отчет" + System.lineSeparator() +
                "3.Сверить отчёты" + System.lineSeparator() +
                "4.Вывести информацию обо всех месячных отчётах" + System.lineSeparator() +
                "5.Вывести информацию о годовом отчёте" + System.lineSeparator() +
                "0.Завершить работу");
    }

    enum Commands {
        END, READ_MONTHS, READ_YEAR, CHECK_REPORTS,
        MONTH_STATISTIC, YEAR_STATISTIC;
    }
}

