public class Savings extends Budget {

    private double goal;

    
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
    
        System.out.println("--- SAVINGS GOAL ---");
        System.out.println("Name: " + getName());
        System.out.println("Current Balance: " + getBalance());
        System.out.println("Target Goal: " + goal);

        if (goal > 0) {
            double progress = (getBalance() / goal) * 100;
            
            System.out.printf("Progress : " + progress + "%");
        }

        double remaining = getRemainingToGoal();
        if (remaining > 0) {
            System.out.println("Left to save: " + remaining);
        } else {
            System.out.println("Goal reached! ");
        }
    }

    public double getRemainingToGoal() {
        double remaining = goal - getBalance();
        return Math.max(0, remaining); 
    }
}








