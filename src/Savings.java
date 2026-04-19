public class Savings extends Budget {

    private double goal;

    // Обязательно для JSON
    public Savings() {
        super();
    }

    public Savings(String name, double balance, double goal) {
        super(name, balance);
        this.goal = goal;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal){
        this.goal = goal;
    }

    @Override
    public void displayInfo(){
        // Используем super.displayInfo(), если хочешь вывести базовую инфу,
        // либо переопределяем полностью, как у тебя:
        System.out.println("--- SAVINGS GOAL ---");
        System.out.println("Name: " + getName());
        System.out.println("Current Balance: " + getBalance());
        System.out.println("Target Goal: " + goal);

        if (goal > 0) {
            double progress = (getBalance() / goal) * 100;
            // Округлим до 1 знака после запятой для красоты
            System.out.printf("Progress: %.1f%%\n", progress);
        }

        double remaining = getRemainingToGoal();
        if (remaining > 0) {
            System.out.println("Left to save: " + remaining);
        } else {
            System.out.println("Goal reached! 🎉");
        }
    }

    public double getRemainingToGoal() {
        double remaining = goal - getBalance();
        return Math.max(0, remaining); // Более элегантный способ вернуть 0, если баланс выше цели
    }
}








