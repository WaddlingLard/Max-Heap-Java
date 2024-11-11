import java.util.ArrayList;

/** 
 * This class utlizes the heap sorting structure and uses a generic type allowing it to store
 * different values. This is a working implementation for the ADT.
 * 
 * @author Brian Wu
*/
public class MaxHeap<T extends Comparable<T>>{
    
    protected ArrayList<T> items;

    /**
     * Default Constructor. No Frills.
     */
    public MaxHeap(){
        items = new ArrayList<T>();
    }

    /**
     * Overloaded constructor that takes in an unsorted generic Arraylist and calls the buildMaxHeap() method.
     * @param unsorted Generic ArrayList needed to be sorted as a heap
     */
    public MaxHeap(ArrayList<T> unsorted){
        items = unsorted;
        buildMaxHeap(items);
    }
    
    /**
     * This method checks if the current object i is violating the MaxHeap property. It checks it's children (left and right)
     * using the compareTo method.
     * @param i Index of the item to be checked
     */
    private void maxHeapify(int i){ //Because this is a max-heap that means the largest values will be at the top
        T compare1, compare2;
        int largest;
        int left = getLeftChild(i);
        int right = getRightChild(i);
       
        largest = i; //default largest value is i
        compare1 = items.get(left);
        compare2 = items.get(i);
        if(left < items.size() & compare1.compareTo(compare2) > 0){ //comparing left child and i
            largest = left;
        }
        compare1 = items.get(right);
        compare2 = items.get(largest);

        if(right < items.size() & compare1.compareTo(compare2) > 0){ //comparing right child and largest
            largest = right;
        }
            
        if(largest != i){ //swap if i isnt the biggest value
            T temp = items.get(largest);
            items.set(largest, items.get(i));
            items.set(i, temp);
            maxHeapify(largest);
        }
    }
    

    /**
     * Builder of the heap that loops through the front half of the ArrayList. 
     * @param itemsUnsorted ArrayList with unsorted values.
     */
    private void buildMaxHeap(ArrayList<T> itemsUnsorted){ 
        for(int i = (items.size() / 2) - 1; i >= 0; i--){
            maxHeapify(i);
        }
    }

    /**
     * Sort method that takes out all of the values and returns them in sorted order.
     */
    public void heapSort(){
        buildMaxHeap(items);
        for(int i = items.size() - 1; i > 2;){  
            T temp = items.get(0);
            items.set(0, items.get(i));
            items.set(i, temp);
            items.remove(items.size() - 1);
            maxHeapify(i);
        }
    }

    /**
     * Return the maximum by accessing the index of the root which is 0.
     * @return T element that is the root of the heap
     * @throws HeapException If items.size() is less than 1.
     */
    public T heapMaximum() throws HeapException{
        if(items.size() < 1){
            throw new HeapException("heap underflow");
        }
        return items.get(0);
    }

    /**
     * Extracts the max by calling the heapMaximum() method and swaps with the last element in 
     * the heap. It then removes the maximum from the heap and calls maxHeapify() on the new root. 
     * @return T element that is the maximum of the heap.
     */
    public T heapExtractMax(){
        T max = null;
        try {
            max = heapMaximum();
        } catch (HeapException e) {
            e.printStackTrace();
        }
        items.set(0, items.get(items.size() - 1));
        items.remove(items.size() - 1);
        if(items.isEmpty()){
        }else{
            maxHeapify(0);    
        }
        return max;
    }

    /**
     * Increases the key (in this case priority level) and keeps in mind the other elements around it
     * if there is something that needs to be swapped this method follows through. This method also has a
     * creative way of handling a generic ArrayList by taking a quick glance.
     * @param x The element that's key is being increased.
     * @param i The key value that is compared with the T's key value.
     * @throws HeapException If the key (i) provided is less than the key of the provided element (x).
     */
    public void heapIncreaseKey(T x, int i) throws HeapException{
        Task tempTask, tempTask2;
        tempTask2 = new Task(null, 0, null);
        if(x.getClass() == tempTask2.getClass()){ //For this class this was my "solution" to deal with a generic value
            tempTask = (Task) x;
            if(i < tempTask.getPriority()){
                throw new HeapException("new key must be larger than current key");
            }
            int location = items.indexOf(x);
            int parentLocation = getParent(location);
            Task compare1, compare2;
            compare1 = (Task) items.get(getParent(location));
            compare2 = (Task) items.get(location);  
            compare2.setPriority(i);
            
            while(location > 0 && compare1.getPriority() <= compare2.getPriority()){
                if(compare1.getPriority() == compare2.getPriority() && compare1.getHourCreated() < compare2.getHourCreated()){ //In the rare instance
                    location = -1;
                }else if(compare1.getHourCreated() > compare2.getHourCreated()){
                    items.set(parentLocation,(T) compare2);
                    items.set(location,(T) compare1);
                    location = parentLocation;
                    parentLocation = getParent(location);
                    compare1 = (Task) items.get(getParent(location));
                    compare2 = (Task) items.get(location); 
                }else{
                    items.set(parentLocation,(T) compare2);
                    items.set(location,(T) compare1);
                    location = parentLocation;
                    parentLocation = getParent(location);
                    compare1 = (Task) items.get(getParent(location));
                    compare2 = (Task) items.get(location); 
                }
            } 
        }else{ //If T isn't a task it treats the value as an integer. Further implementation must be taken if other types are considered
            Integer tempNumInteger = 0;
            if(x.getClass() == tempNumInteger.getClass()){
                tempNumInteger = (Integer) x;
                if(tempNumInteger.compareTo(i) >= 0){ 
                    throw new HeapException("new key must be larger than current key");
                }
                tempNumInteger = i;
                int location = items.indexOf(x);
                int parentLocation = getParent(location);
                items.set(location,(T) tempNumInteger);
                while(location > 0 && items.get(parentLocation).compareTo(items.get(location)) < 0){ 
                    T temp = items.get(location);
                    items.set(location, items.get(parentLocation));
                    items.set(parentLocation, temp);
                    location = parentLocation;
                    parentLocation = getParent(location);
                }   
            }  
        }
    }

    /**
     * Inserts the provided element (T) into the heap and increases the key by calling the heapIncreaseKey()
     * method.
     * @param x The element that is to be inserted into the heap.
     * @throws HeapException  If the key (k) provided is less than the key of the provided element (x).
     */
    public void heapInsert(T x){ 
        Task temp, temp2;
        temp2 = new Task(null, 0, null);
        Integer tempNumInteger = 0;
        if(x.getClass() == temp2.getClass()){
            temp = (Task) x;
            int k = temp.getPriority(); 
            temp.setPriority(Integer.MIN_VALUE);
            items.add(x);
            try{
                heapIncreaseKey(x, k); 
            }catch(HeapException e){
                System.out.println("new key must be larger than current key");
            }
        }else if(x.getClass() == tempNumInteger.getClass()){
            tempNumInteger = (Integer) x;
            int k = tempNumInteger;
            tempNumInteger = Integer.MIN_VALUE;
            items.add((T) tempNumInteger);
            try{
                heapIncreaseKey((T) tempNumInteger, k); 
            }catch(HeapException e){
                System.out.println("new key must be larger than current key");
            }
        }  
    }

    /**
     * Returns true or false whether the heap is empty or not.
     * @return A boolean value.
     */
    public boolean isEmpty(){
        return items.isEmpty();
    }

    /**
     * Finds the parent index for the current index called in the heap.
     * @param i The index of the current value. 
     * @return The parent index.
     */
    private int getParent(int i){
        if(i == 0){
            return 0;
        }
        return (i - 1) / 2;
    }

    public ArrayList<T> getItems(){
        ArrayList<T> copy = new ArrayList<T>();
        for(int i = 0; i < items.size(); i++){
            copy.add(items.get(i));
        }
        return copy;
    }

    /**
     * Find the left child index for the current index called in the heap. If it doesn't have one
     * the method returns the value it called (i).
     * @param i The index of the current value.
     * @return The left child index.
     */
    private int getLeftChild(int i){
        int temp = (2 * i) + 1;
        if(temp < items.size()){
            return temp;
        }
        return i;
    }

    /**
     * Find the right child index for the current index called in the heap. If it doesn't have one
     * the method return the value it called (i).
     * @param i The index of the current value.
     * @return The right child index.
     */
    private int getRightChild(int i){ 
        int temp = (2 * i) + 2;
        if(temp < items.size()){
            return temp;
        }
        return i;
    }

    
}
