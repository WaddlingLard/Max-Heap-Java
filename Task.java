/**
 * A class that is for the Task object. This task is an object that contains values TaskType,
 * description, priorityLevel, and hourCreated. This object is also comparable. The documentation
 * for the methods provided is in the TaskInterface.
 * 
 * @author Brian Wu
 */
public class Task implements TaskInterface, Comparable<Task>{
    private TaskType type;
    private int priorityLevel, waitingTime, hourCreated;
    private String description;

    /**
     * Default Constructor.
     */
    public Task(){
    }

    /**
     * Overloaded Constructor that has the type, hour created, and description provided.
     * @param taskName Type of the task from the TaskInterface.
     * @param hourCreated Time when the task was created.
     * @param description Description of the task to make in unique.
     */
    public Task(TaskType taskName, int hourCreated, String description){
        type = taskName;
        priorityLevel = waitingTime = 0;
        this.description = description;
        this.hourCreated = hourCreated;
    }

    /**
     * Comparable method that checks the Task's priorityLevel and hourCreated value. In order
     * to avoid equal values the compareTo method then uses the hourCreated to clear any tie breakers.
     */
    public int compareTo(Task taskCompare){

        if(taskCompare.getPriority() == priorityLevel){
            if(taskCompare.getHourCreated() < hourCreated){
                return -1;
            }else{
                return 1;
            }
        }
        
        if(taskCompare.getPriority() > priorityLevel){
            return -1;
        }
        return 1;
    }

    public int getPriority(){
        return priorityLevel;
    }

    public void setPriority(int priority){
        this.priorityLevel = priority;
    }

    public TaskInterface.TaskType getTaskType(){
        return type;
    }

    public void incrementWaitingTime(){
        this.waitingTime++;
    }

    public void resetWaitingTime(){
        this.waitingTime = 0;
    }

    public int getWaitingTime(){
        return waitingTime;
    }

    /**
     * Returns the description of the task.
     * @return A String value. 
     */
    public String getTaskDescription(){
        return description;
    }

    /**
     * 
     * @return
     */
    public int getHourCreated(){
        return hourCreated;
    }
    
    public String toString() {
        return type + " " + description + " at Hour: " + hourCreated + ":00"; 
    }


}
