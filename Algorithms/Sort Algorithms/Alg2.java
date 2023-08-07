import java.util.Random;

public class Alg2 {
	public static void quickSort(int[] array, int low, int high) {
        int stackLength = high - low + 1;
        int[] stackArr = new int[stackLength];
        int top = -1;
        stackArr[++top] = low;
        stackArr[++top] = high;
        while (top >= 0) {
            high = stackArr[top--];
            low = stackArr[top--];
            int pivot = partition(array,low,high);
            if (pivot-1 > low) {
                stackArr[++top] = low;
                stackArr[++top] = pivot-1;
            }
            if (pivot+1 < high) {
                stackArr[++top] = pivot+1;
                stackArr[++top] = high;
            }
        }
    }
    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low-1;
        for (int j = low; j < high; j++) {
            if(array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i+1, high);
        return i+1;
    }
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
