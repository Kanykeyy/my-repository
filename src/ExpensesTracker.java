import java.util.ArrayList;

public class ExpensesTracker {

    private ArrayList<Expense> expenses;
    private double totalSpent;

    // Оставляем ОДИН конструктор по умолчанию
    public ExpensesTracker() {
        this.expenses = new ArrayList<>();
        this.totalSpent = 0.0;
    }

    // --- ГЕТТЕРЫ ---
    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    // --- СЕТТЕРЫ (для работы Jackson) ---
    public void setExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    // --- МЕТОДЫ ЛОГИКИ ---
    public void addExpense(Expense expense) {
        if (expense != null) {
            // На всякий случай проверяем, не null ли список (если Jackson его не создал)
            if (this.expenses == null) {
                this.expenses = new ArrayList<>();
            }
            expenses.add(expense);
            totalSpent += expense.getAmount();
        }
    }

    public void showAllExpenses() {
        if (expenses == null || expenses.isEmpty()) {
            System.out.println("No expenses tracked yet.");
            return;
        }
        for (Expense e : expenses) {
            e.displayInfo();
        }
    }
}






