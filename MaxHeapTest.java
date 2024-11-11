import java.util.ArrayList;

/**
 * This is a simple unit test plan that inserts integers into the heap and verifies if the heap methods property
 * is satisfied for a maxHeap and that all the methods work properly. There are several cases where Tasks are 
 * implemented as well but they were more an after thought.
 * 
 * @author Brian Wu
 */
public class MaxHeapTest {
    
    public static void main(String[] args){

        MaxHeap heap, heap2, heap3, heap4, heap5, heap6, heap7, heap8, heap9, heap10, heap11, heap12;
        ArrayList check, check2, check3;

        heap = new MaxHeap<>(); //testing is empty
        if(heap.isEmpty()){
            System.out.println("TEST 1 PASSED!");
        }

        ArrayList test2 = new ArrayList(); //testing is empty
        test2.add(2);
        heap2 = new MaxHeap<>(test2);
        if(!heap2.isEmpty()){
            System.out.println("TEST 2 PASSED!");
        }

        heap3 = new MaxHeap<>(); //testing insert
        heap3.heapInsert(2);
        heap3.heapInsert(6);
        check = heap3.getItems();
        if(check.get(0).equals(6) & check.get(1).equals(2)){
            System.out.println("TEST 3 PASSED!");
        }

        // heap3 = new MaxHeap();
        // Task task1, task2, task3, task4, task5;
        // task1 = new Task(TaskType.MINING, 0, "task1");
        // task2 = new Task(TaskType.FORAGING, 0, "task2");
        // heap3.heapInsert(task1);
        // heap3.heapInsert(task2);
        // check = heap3.getItems();
        // if(check.get(0).equals(task1) && check.get(1).equals(task2)){
        //     System.out.println("TEST 3 PASSED!");
        // }
        

        heap4 = new MaxHeap<>(); //testing extractMax
        heap4.heapInsert(2);
        heap4.heapInsert(6);
        check2 = heap4.getItems();
        Integer temp = (Integer) heap4.heapExtractMax();
        if(temp == 6){
            System.out.println("TEST 4 PASSED!");
        }

        // heap4 = new MaxHeap<>();
        // task1 = new Task(TaskType.MINING, 0, "task1");
        // task2 = new Task(TaskType.FORAGING, 0, "task2");
        // heap4.heapInsert(task1);
        // heap4.heapInsert(task2);
        // check2 = heap4.getItems();
        // Task temp = (Task) heap4.heapExtractMax();
        // if(temp.equals(task1)){
        //     System.out.println("TEST 4 PASSED!");
        // }

        heap5 = new MaxHeap<>(); //testing extractMax
        heap5.heapInsert(5);
        heap5.heapInsert(2);
        heap5.heapInsert(8);
        Integer temp1, temp2, temp3;
        temp1 = (Integer) heap5.heapExtractMax();
        temp2 = (Integer) heap5.heapExtractMax();
        temp3 = (Integer) heap5.heapExtractMax();
        if(temp1 == 8 & temp2 == 5 & temp3 == 2){
            System.out.println("TEST 5 PASSED!");
        }

        
        // heap6 = new MaxHeap<>();
        // heap6.heapInsert(3);
        // heap6.heapInsert(10);
        // try {
        //     heap6.heapIncreaseKey(3, 12);
        //     if(heap6.heapMaximum().equals(12)){
        //         System.out.println("TEST 6 PASSED!");
        //     }
        // } catch (Exception e) {
        //     System.out.println("new key must be larger than current key");
        // }

        // heap6 = new MaxHeap<>();
        // task1 = new Task(TaskType.MINING, 0, "task1");
        // task2 = new Task(TaskType.FORAGING, 0, "task2");
        // heap6.heapInsert(task1);
        // heap6.heapInsert(task2);
        // try {
        //     heap6.heapIncreaseKey(task2, 1);
        //     if(heap6.heapExtractMax().equals(task2)){
        //         System.out.println("TEST 6 PASSED!");
        //     }
        // } catch (Exception e) {
        //     System.out.println("new key must be larger than current key");
        // }


        heap7 = new MaxHeap<>(); //testing increaseKey
        heap7.heapInsert(6);
        heap7.heapInsert(1);
        heap7.heapInsert(25);
        try {
            heap7.heapIncreaseKey(6, 2050);
            if(heap7.heapMaximum().equals(2050)){
                System.out.println("TEST 7 PASSED!");
            }
        } catch (Exception e) {
            System.out.println("new key must be larger than current key");
        }    

        // heap7 = new MaxHeap<>();
        // task1 = new Task(TaskType.MINING, 0, "task1");
        // task2 = new Task(TaskType.FORAGING, 0, "task2");
        // task3 = new Task(TaskType.FEEDING, 2, "task3");
        // heap7.heapInsert(task1);
        // heap7.heapInsert(task2);
        // heap7.heapInsert(task3);
        // try {
        //     heap7.heapIncreaseKey(task3, 1);
        //     if(heap7.heapExtractMax().equals(task3)){
        //         System.out.println("TEST 7 PASSED!");
        //     }
        // } catch (Exception e) {
        //     System.out.println("new key must be larger than current key");
        // }

        heap8 = new MaxHeap<>(); //testing sorted and isMaxHeap on ArrayList with ordered insertion
        for(int i = 1; i < 21; i++){
            heap8.heapInsert(i);
        }
        if(checkIfMaxHeap(heap8) == true && checkIfSorted(heap8) == true){
            System.out.println("TEST 8 PASSED!");
        }
        
        heap9 = new MaxHeap<>(); //testing sorted and isMaxHeap on ArrayList with reverse ordered insertion
        for(int i = 21; i > 0; i--){ 
            heap9.heapInsert(i);
        }
        if(checkIfMaxHeap(heap9) == true && checkIfSorted(heap9) == true){
            System.out.println("TEST 9 PASSED!");
        }

        heap10 = new MaxHeap<>(); //testing sorted and isMaxHeap on ArrayList with random insertion
        for(int i = 1; i < 21; i++){ 
            int insert = (int) (Math.random() * 100);
            heap10.heapInsert(insert);
        }
        if(checkIfMaxHeap(heap10) == true && checkIfSorted(heap10) == true){
            System.out.println("TEST 10 PASSED!");
        }     
    }

    /**
     * Verifies if the heap doesn't violate the max heap property. This method is only setup for
     * integer values.
     * @param heap The heap to be verified.
     * @return A boolean value.
     */
    public static boolean checkIfMaxHeap(MaxHeap heap){
        ArrayList check = heap.getItems();
        for(int i = 0; i < check.size(); i++){
            int left = (2 * i) + 1;
            if(left >= check.size()){
                left = i;
            }
            int right = (2 * i) + 2;
            if(right >= check.size()){
                right = i;
            }
            Integer getI, getLeft, getRight;
            getI = (Integer) check.get(i);
            getLeft = (Integer) check.get(left);
            getRight = (Integer) check.get(right);
            if(getI.compareTo(getLeft) != -1){
            }else if(i == left){
            }else{ return false;}

            if(getI.compareTo(getRight) != -1){
            }else if(i == right){
            }else{ return false;}
        }
        return true;
    }

    /**
     * Verified if the heap is sorted by continuously calling the heapExtractMax() method. This
     * method is only setup for integer values.
     * @param heap The heap to be verified.
     * @return A boolean value.
     */
    public static boolean checkIfSorted(MaxHeap heap){
        ArrayList check = new ArrayList<>();
        while(!heap.isEmpty()){
            check.add(heap.heapExtractMax());
        }
        Integer compare1, compare2;
        for(int i = 0; i < check.size() - 1; i++){
            compare1 = (Integer) check.get(i);
            compare2 = (Integer) check.get(i + 1);
            if(compare1.compareTo(compare2) == -1){
                return false;
            }
        }
        return true;

    }
}
