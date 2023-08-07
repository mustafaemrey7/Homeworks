import java.util.ArrayList;
import java.util.Collections;
public class Planner {

    public final Task[] taskArray;
    public final Integer[] compatibility;
    public final Double[] maxWeight;
    public final ArrayList<Task> planDynamic;
    public final ArrayList<Task> planGreedy;
    public final Double[] M;

    public Planner(Task[] taskArray) {
        // Should be instantiated with an Task array
        // All the properties of this class should be initialized here
        this.taskArray = taskArray;
        this.compatibility = new Integer[taskArray.length];
        maxWeight = new Double[taskArray.length];
        this.M=new Double[taskArray.length];
        this.planDynamic = new ArrayList<>();
        this.planGreedy = new ArrayList<>();  
        calculateCompatibility();
    	System.out.println("Calculating max array");
		System.out.println("---------------------");
		calculateMaxWeight(taskArray.length-1);
       
    }
    /**
     * @param index of the {@link Task}
     * @return Returns the index of the last compatible {@link Task},
     * returns -1 if there are no compatible {@link Task}s.
     */
    public int binarySearch(int index) {
        // YOUR CODE HERE
    	int low = 0;
        int high = index - 1;
        int compatibleIndex = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            String midFinishTime = taskArray[mid].getFinishTime();
            String indexStartTime = taskArray[index].getStartTime();
            if (convertTimeToMinutes(midFinishTime) <= convertTimeToMinutes(indexStartTime)) {
                compatibleIndex = mid; // update the compatible index
                low = mid + 1; // search for a later compatible task
            } else {
                high = mid - 1; // search for an earlier compatible task
            }
        }
        return compatibleIndex;
    }
    /**
     * {@link #compatibility} must be filled after calling this method
     */
    public void calculateCompatibility() {
    	for (int i = 0; i < taskArray.length; i++) {
    	    compatibility[i] = binarySearch(i);
    	    
    	}
    }
    /**
     * Uses {@link #taskArray} property
     * This function is for generating a plan using the dynamic programming approach.
     * @return Returns a list of planned tasks.
     */
    public ArrayList<Task> planDynamic() {
    	System.out.println();
    	System.out.println("Calculating the dynamic solution");
    	System.out.println("--------------------------------"); 
    	solveDynamic(taskArray.length-1);
    	Collections.reverse(planDynamic);
    	System.out.println();
    	System.out.println("Dynamic Schedule");
    	System.out.println("----------------");		
    	for (int i = 0; i < planDynamic.size(); i++) {
			System.out.println("At "+planDynamic.get(i).getStartTime()+", "+ planDynamic.get(i).getName()+".");
		}
    	return planDynamic;
    }
    /**
     * {@link #planDynamic} must be filled after calling this method
     */
    public void solveDynamic(int i) {
    	if (i==-1) {
			return;
		}
    	System.out.println("Called solveDynamic(" + i + ")");
    	if (i==0) {
    		planDynamic.add(taskArray[i]);
			return;
		}
    	else if (maxWeight[i] > maxWeight[i-1]) {
			planDynamic.add(taskArray[i]);
			solveDynamic(compatibility[i]);
		}
    	else {
			solveDynamic(i-1);			
		}
    }

    /**
     * {@link #maxWeight} must be filled after calling this method
     */
    /* This function calculates maximum weights and prints out whether it has been called before or not  */
    public Double calculateMaxWeight(int i) {
    	System.out.println("Called calculateMaxWeight("+i+")");
        if (i == -1) {
            return 0.0;
        }
        if (maxWeight[i]==null||i==0) {
        	Double maxWeightC = Math.max(calculateMaxWeight(compatibility[i]) + taskArray[i].getWeight(), calculateMaxWeight(i - 1));   
        	maxWeight[i] = maxWeightC; 
        	return maxWeightC;
		}
        else { 
        	return maxWeight[i];
        	}    	
    }

    /**
     * {@link #planGreedy} must be filled after calling this method
     * Uses {@link #taskArray} property
     *
     * @return Returns a list of scheduled assignments
     */

    /*
     * This function is for generating a plan using the greedy approach.
     * */
    public ArrayList<Task> planGreedy() {
    	int h = 0;
    	planGreedy.add(taskArray[0]);
    	for (int i = 0; i < compatibility.length; i++) {
			if (compatibility[i]>=h) {
				planGreedy.add(taskArray[i]);
				h=i;	
			}
		}
    	System.out.println();
    	System.out.println("Greedy Schedule");
    	System.out.println("---------------");			
    	for (int i = 0; i < planGreedy.size(); i++) {
			System.out.println("At "+planGreedy.get(i).getStartTime()+", "+ planGreedy.get(i).getName()+".");
		}
        // YOUR CODE HERE
        return planGreedy;
    }
    private int convertTimeToMinutes(String timeString) {
        String[] parts = timeString.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}
