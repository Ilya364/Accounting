public class Checker {
    void check(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        if (monthlyReport.monthsToTransactions.isEmpty() || yearlyReport.monthSummaries.isEmpty()) {
            System.out.println("Сначала необходимо считать отчеты.");
            return;
        }

        int i = 0;
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
            if (yearlyReport.monthSummaries.get(i).is_expense && sumExpense != yearlyReport.monthSummaries.get(i).amount) {
                System.out.println("Несоответствие расходов в месяце: " + month);
                isError = true;
            } else if (!yearlyReport.monthSummaries.get(i).is_expense && sumIncome != yearlyReport.monthSummaries.get(i).amount) {
                System.out.println("Несоответствие доходов в месяце: " + month);
                isError = true;
            } else if (yearlyReport.monthSummaries.get(i + 1).is_expense && sumExpense != yearlyReport.monthSummaries.get(i + 1).amount) {
                System.out.println("Несоответствие расходов в месяце: " + month);
                isError = true;
            } else if (!yearlyReport.monthSummaries.get(i + 1).is_expense && sumIncome != yearlyReport.monthSummaries.get(i + 1).amount) {
                System.out.println("Несоответствие доходов в месяце: " + month);
                isError = true;
            }
            i += 2;
        }

        if (!isError)
            System.out.println("Проверка пройдена успешно!");
    }
}
