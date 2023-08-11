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
            int command = scanner.nextInt();
            switch (command) {
                case 1:
                    for (int month = 1; month <= numberOfMonthlyReports; month++) {
                        monthlyReport.addMonthlyReport(months[month - 1], "m.20210" + month + ".csv");
                    }
                    break;
                case 2:
                    yearlyReport2021.addYearlyReport(2021, "y.2021.csv");
                    break;
                case 3:
                    checker.check(monthlyReport, yearlyReport2021);
                    break;
                case 4:
                    monthlyReport.printStatistic();
                    break;
                case 5:
                    yearlyReport2021.printStatistic();
                    break;
                case 0:
                    System.out.println("Работа завершена.");
                    return;
                default:
                    System.out.println("Такой команды нет.");
                    break;
            }
        }
    }

    static void printMenu() {
        System.out.println("1.Считать все месячные отчеты\n" +
                "2.Считать годовой отчет\n" +
                "3.Сверить отчёты\n" +
                "4.Вывести информацию обо всех месячных отчётах\n" +
                "5.Вывести информацию о годовом отчёте\n" +
                "0.Завершить работу");
    }
}

