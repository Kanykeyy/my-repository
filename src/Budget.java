import java.util.ArrayList;

public class Budget {

    private String name;
    private double balance;
    private ArrayList<Expense> expenses;

    // Конструктор по умолчанию (обязателен для JSON)
    public Budget() {
        this.expenses = new ArrayList<>();
    }

    public Budget(String name, double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
        this.expenses = new ArrayList<>();
    }

    // --- ГЕТТЕРЫ ---
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    // --- СЕТТЕРЫ (теперь Jackson сможет заполнять поля) ---

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    // --- ЛОГИКА ---

    public void addExpense(Expense expense) {
        if (expense != null && expense.getAmount() > 0) {
            expenses.add(expense);
            balance -= expense.getAmount();
        } else {
            System.out.println("Invalid expense");
        }
    }

    public void removeExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            Expense e = expenses.remove(index);
            balance += e.getAmount();
        } else {
            System.out.println("Invalid index");
        }
    }

    public void showExpenses() {
        System.out.println("--- Expenses for " + name + " ---");
        if (expenses.isEmpty()) {
            System.out.println("No expenses yet.");
            return;
        }
        for (int i = 0; i < expenses.size(); i++) {
            System.out.print(i + ": ");
            expenses.get(i).displayInfo();
        }
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
        System.out.println("Expenses count: " + expenses.size());
    }
}




