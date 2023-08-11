public class Transaction {
    String name;
    boolean is_expense;
    int quantity;
    int unitPrice;

    public Transaction(String name, boolean is_expense, int quantity, int unitPrice) {
        this.name = name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
