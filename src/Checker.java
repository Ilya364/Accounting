public class Checker {
    void check(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        if (monthlyReport.monthsToTransactions.isEmpty()) {
            System.out.println("Необходимо считать месячные отчеты.");
            return;
        } else if (yearlyReport.monthSummaries.isEmpty()) {
            System.out.println("Необходимо считать годовой отчет.");
            return;
        }

        int line = 0;
        boolean isError = false;
        for (String month : monthlyReport.monthsToTransactions.keySet()) {
            int sumIncome = 0;
            int sumExpense = 0;
            for (Transaction transaction : monthlyReport.monthsToTransactions.get(month)) {
                if (transaction.is_expense)
                    sumExpense += transaction.quantity * transaction.unitPrice;
                else
                    sumIncome += transaction.quantity * transaction.unitPrice;
            }
            line = yearlyReport.months.indexOf(month) * 2;
            if (isExpense(yearlyReport, line) && sumExpense != getAmount(yearlyReport, line)) {
                System.out.println("Несоответствие расходов в месяце: " + month);
                isError = true;
            } else if (!isExpense(yearlyReport, line) && sumIncome != getAmount(yearlyReport, line)) {
                System.out.println("Несоответствие доходов в месяце: " + month);
                isError = true;
            } else if (isExpense(yearlyReport, line + 1) && sumExpense != getAmount(yearlyReport, line + 1)) {
                System.out.println("Несоответствие расходов в месяце: " + month);
                isError = true;
            } else if (!isExpense(yearlyReport, line + 1) && sumIncome != getAmount(yearlyReport, line + 1)) {
                System.out.println("Несоответствие доходов в месяце: " + month);
                isError = true;
            }
            line += 2;
        }

        if (!isError)
            System.out.println("Проверка пройдена успешно!");
    }

    boolean isExpense(YearlyReport yearlyReport, int line) {
        return yearlyReport.monthSummaries.get(line).is_expense;
    }

    int getAmount(YearlyReport yearlyReport, int line) {
        return yearlyReport.monthSummaries.get(line).amount;
    }
}
