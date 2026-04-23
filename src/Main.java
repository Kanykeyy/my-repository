import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    private static ArrayList<Budget> budgets = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n-- MENU --");
            System.out.println("1. Create Budget");
            System.out.println("2. Show Budgets");
            System.out.println("3. Manage Budget");
            System.out.println("4. Save to JSON");
            System.out.println("5. Load from JSON");
            System.out.println("0. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> createBudget();
                case 2 -> showBudgets();
                case 3 -> manageBudget();
                case 4 -> saveToJson();
                case 5 -> loadFromJson();
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    // CREATE
    private static void createBudget() {

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();

        budgets.add(new Budget(name, balance));
    }

    // READ
    private static void showBudgets() {

        for (int i = 0; i < budgets.size(); i++) {
            System.out.print(i + ": ");
            budgets.get(i).displayInfo();
        }
    }

    // MANAGE
    private static void manageBudget() {

        if (budgets.isEmpty()) {
            System.out.println("No budgets yet!");
            return;
        }

        showBudgets();

        System.out.print("Choose index: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 0 || index >= budgets.size()) {
            System.out.println("Invalid index");
            return;
        }

        Budget b = budgets.get(index);

        while (true) {

            System.out.println("\n--- " + b.getName() + " ---");
            System.out.println("1. Add Expense");
            System.out.println("2. Show Expenses");
            System.out.println("3. Remove Expense");
            System.out.println("0. Back");

           int choice;
while (!sc.hasNextInt()) {
    System.out.println("Error: Please enter a valid number (1-6):");
    sc.next(); 
                         }
           choice = sc.nextInt();
           sc.nextLine();

            switch (choice) {
                case 1 -> addExpense(b);
                case 2 -> b.showExpenses();
                case 3 -> removeExpense(b);
                case 0 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void addExpense(Budget b) {

        System.out.print("Expense name: ");
        String name = sc.nextLine();

        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        b.addExpense(new Expense(name, amount));
    }

    private static void removeExpense(Budget b) {

        b.showExpenses();

        System.out.print("Index: ");
        int index = sc.nextInt();
        sc.nextLine();

        b.removeExpense(index);
    }

    // SAVE JSON
    private static void saveToJson() {
        try {
            mapper.writeValue(new File("budgets.json"), budgets);
            System.out.println("Saved to JSON!");
        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }

    // LOAD JSON
    private static void loadFromJson() {
        try {
            Budget[] arr = mapper.readValue(
                    new File("budgets.json"),
                    Budget[].class
            );

            budgets = new ArrayList<>(Arrays.asList(arr));

            System.out.println("Loaded from JSON!");
        } catch (Exception e) {
            System.out.println("Error loading file");
        }
    }
}
