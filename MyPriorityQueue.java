import java.util.ArrayList;

/**
 * A priority queue class that utlizes the generic MaxHeap class. It is the front end of the program
 * that allows the user to use methods like enqueue, dequeue, isEmpty, and update. It is also capable of
 * outputing the MaxHeap when the debugger mode is turned on in MyLifeInStardew. The documenation of the
 * methods are in the PriorityQueueInterface.
 * 
 * @author Brian Wu
 */
public class MyPriorityQueue<T> extends MaxHeap implements PriorityQueueInterface{

    /**
     * Default Constructor
     */
    public MyPriorityQueue(){
        new MaxHeap<Task>();
    }

    public void enqueue(Object task) {
        Task element = (Task) task;
        heapInsert(element);
    }

    public Task dequeue(){ 
        Task newTask = (Task) heapExtractMax();
        return newTask;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void update(int timeToIncrementPriority, int maxPriority) {
        Task item;
        for(int i = 0; i < items.size(); i++){ //looping through all tasks
            item = (Task) items.get(i);
            item.incrementWaitingTime();
            if(item.getWaitingTime() >= timeToIncrementPriority){ //make sure to reset after true
                item.resetWaitingTime();
                if(item.getPriority() < maxPriority){
                int k = item.getPriority() + 1;
                item.setPriority(item.getPriority() + 1);

                try {
                    heapIncreaseKey(item, k);
                } catch (Exception e) {
                    System.out.println("new key must be larger than current key");
                }

                }   
            }
        }
    }   

    public String toString(){
        String message = "[";
        for(int i = 0; i < items.size(); i++){
            Task temp = (Task) items.get(i);
            message = message.concat(temp.toString() + " " + temp.getPriority() + ", ");
        }
        message = message.concat("]");

        return message;
    }
}
