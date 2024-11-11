import java.util.Random;

/**
 * This class generates task values with the task class. TaskGeneratorInterface contains the
 * documentation for all the provided methods.
 * 
 * @author Brian Wu
 */
public class TaskGenerator implements TaskGeneratorInterface{
    private Task currentTask;
    private int currentEnergyStorage;
    private double probability;
    private long seedLevel;
    private Random randomNum;

    /**
     * Default Constructor.
     */
    public TaskGenerator(){
    }

    /**
     * Overloaded Constructor with a couple addons like a chance value for task
     * generation and a seed in order to replicate for testing.
     * @param chance
     * @param seed
     */
    public TaskGenerator(double chance, long seed){
        currentEnergyStorage = DEFAULT_ENERGY;
        probability = chance;
        seedLevel = seed;
        randomNum = new Random(seedLevel);
    }

    /**
     * Overloaded Constructor that takes in a task generation value.
     */
    public TaskGenerator(double chance){
        currentEnergyStorage = DEFAULT_ENERGY;
        probability = chance;
    }

    public Task getNewTask(int hourCreated, TaskInterface.TaskType taskType, String taskDescription) {
        currentTask = new Task(taskType, hourCreated, taskDescription);
        return currentTask;
    }

    public void decrementEnergyStorage(TaskInterface.TaskType taskType) {
        currentEnergyStorage = currentEnergyStorage - taskType.getEnergyPerHour();
    }

    public void resetCurrentEnergyStorage() {
        currentEnergyStorage = DEFAULT_ENERGY;
    }

    public int getCurrentEnergyStorage() {
        return currentEnergyStorage;
    }

    public void setCurrentEnergyStorage(int newEnergyNum) {
        currentEnergyStorage = newEnergyNum;
    }

    public boolean generateTask() {
        if(randomNum.nextDouble() <= probability){
            return true;
        }
        return false;
    }

    public int getUnlucky(Task task, double unluckyProbability) { 
        double passingOut, dyingProb, test;
        passingOut = task.getTaskType().getPassingOutProbability();
        dyingProb = task.getTaskType().getDyingProbability();
        test = unluckyProbability;
        if(test <= passingOut){
            if(test <= dyingProb && task.getTaskType() == TaskInterface.TaskType.MINING){
                currentEnergyStorage = currentEnergyStorage / 4;
                return DEATH;
            }else{
                currentEnergyStorage = currentEnergyStorage / 2;
                return PASSED_OUT;
            }
        }
        return SURVIVED;
    }

    public String toString(Task task, Task.TaskType taskType) {
        if(taskType == Task.TaskType.MINING) {
            return "     Mining " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
        }
        if(taskType == Task.TaskType.FISHING) {
            return "     Fishing " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")" ;
        }
        if(taskType == Task.TaskType.FARM_MAINTENANCE) {
            return "     Farm Maintenance " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
        }
        if(taskType == Task.TaskType.FORAGING) {
            return "     Foraging " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")" ;
        }
        if(taskType == Task.TaskType.FEEDING) {
            return "     Feeding " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
        }
        if(taskType == Task.TaskType.SOCIALIZING) {
            return "     Socializing " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
        }
        else { return "nothing to see here..."; }
        
    }
}

