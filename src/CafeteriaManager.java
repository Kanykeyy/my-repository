import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

public class CafeteriaManager {

    private static ArrayDeque<String> line = new ArrayDeque<>(); // Очередь людей
    private static HashMap<String, Integer> arrivalTimes = new HashMap<>(); // Имя -> время прибытия
    private static int currentTime = 0; // Текущее время (логическое)
    private static long totalWait = 0;  // Общее время ожидания всех обслуженных
    private static int servedCount = 0; // Количество обслуженных людей

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printHelp(); // Печатаем меню при старте

        // Бесконечный цикл для ввода команд
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toUpperCase(); // Читаем строку, убираем пробелы, делаем uppercase для команд
            String[] parts = input.split("\\s+", 2); // Разбиваем на команду и аргумент (max 2 части)
            String command = parts[0]; // Команда
            String arg = parts.length > 1 ? parts[1].trim() : ""; // Аргумент (имя или минуты), если есть

            // Switch для обработки команд
            switch (command) {
                case "HELP":
                    printHelp();
                    break;
                case "ARRIVE":
                    arrive(arg, false); // Обычное прибытие (в конец)
                    break;
                case "VIP_ARRIVE":
                    arrive(arg, true); // VIP (в начало)
                    break;
                case "SERVE":
                    serve();
                    break;
                case "LEAVE":
                    leave(arg);
                    break;
                case "PEEK":
                    peek();
                    break;
                case "SIZE":
                    System.out.println("Size: " + line.size());
                    break;
                case "PRINT":
                    printQueue();
                    break;
                case "TICK":
                    tick(arg);
                    break;
                case "STATS":
                    stats();
                    break;
                case "EXIT":
                    System.out.println("Goodbye!");
                    scanner.close(); // Закрываем сканер
                    return; // Выходим из main
                default:
                    System.out.println("Unknown command. Type HELP");
            }
        }
    }

    // Метод для печати помощи (список команд)
    private static void printHelp() {
        System.out.println("Cafeteria Line Manager — Commands:");
        System.out.println("HELP - show this menu");
        System.out.println("ARRIVE <name> - person joins the end");
        System.out.println("VIP_ARRIVE <name> - VIP joins the front");
        System.out.println("SERVE - serve the next person");
        System.out.println("LEAVE <name> - person leaves the line");
        System.out.println("PEEK - show next to serve");
        System.out.println("SIZE - show line size");
        System.out.println("PRINT - show full line");
        System.out.println("TICK <minutes> - advance time");
        System.out.println("STATS - show served count and avg wait");
        System.out.println("EXIT - quit program");
    }

    // Метод для прибытия (обычное или VIP)
    private static void arrive(String name, boolean isVip) {
        if (name.isEmpty() || name.contains(" ")) { // Проверка имени
            System.out.println("Error: Invalid name (empty or has spaces)");
            return;
        }
        if (arrivalTimes.containsKey(name)) { // Проверка дубликата
            System.out.println("Name already in system");
            return;
        }
        if (isVip) {
            line.addFirst(name); // В начало
            System.out.print("VIP ");
        } else {
            line.addLast(name); // В конец
        }
        arrivalTimes.put(name, currentTime); // Запоминаем время
        System.out.println(name + " arrived at time " + currentTime + ". Line size = " + line.size());
    }

    // Метод для обслуживания
    private static void serve() {
        if (line.isEmpty()) {
            System.out.println("No one to serve.");
            return;
        }
        String person = line.removeFirst(); // Убираем первого
        int arrival = arrivalTimes.remove(person); // Удаляем из карты
        int wait = currentTime - arrival; // Считаем ожидание
        totalWait += wait; // Добавляем к общему
        servedCount++; // Увеличиваем счётчик
        System.out.println("Served: " + person + " (waited " + wait + " min).");
    }

    // Метод для ухода
    private static void leave(String name) {
        if (name.isEmpty()) {
            System.out.println("Error: Missing name");
            return;
        }
        if (line.remove(name)) { // Удаляем, если нашли (remove возвращает true/false)
            arrivalTimes.remove(name);
            System.out.println(name + " left the line voluntarily. Line size = " + line.size());
        } else {
            System.out.println("Not found");
        }
    }

    // Метод для показа следующего
    private static void peek() {
        if (line.isEmpty()) {
            System.out.println("Line is empty.");
        } else {
            System.out.println("Next: " + line.peekFirst());
        }
    }

    // Метод для печати очереди
    private static void printQueue() {
        System.out.print("Line (front -> back): [");
        boolean first = true;
        for (String person : line) { // Проходим по очереди
            if (!first) System.out.print(", ");
            System.out.print(person);
            first = false;
        }
        System.out.println("]");
    }

    // Метод для продвижения времени
    private static void tick(String arg) {
        if (arg.isEmpty()) {
            System.out.println("Error: Missing minutes");
            return;
        }
        try {
            int minutes = Integer.parseInt(arg); // Парсим число
            if (minutes < 0) {
                System.out.println("Error: Minutes must be non-negative");
                return;
            }
            currentTime += minutes; // Продвигаем время
            System.out.println("Time advanced by " + minutes + " minutes. Current time = " + currentTime);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid integer for minutes");
        }
    }

    // Метод для статистики
    private static void stats() {
        double avgWait = (servedCount == 0) ? 0.00 : (double) totalWait / servedCount; // Среднее (избегаем деления на 0)
        System.out.printf("Served count = %d, Avg wait = %.2f min.\n", servedCount, avgWait);
    }}

