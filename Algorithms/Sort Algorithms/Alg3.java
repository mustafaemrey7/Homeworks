import java.util.ArrayList;
import java.util.Collections;

public class Alg3 {
	public static void bucketSort(int[] A, int n) {
        int numberOfBuckets = n;
        ArrayList<Integer>[] buckets = new ArrayList[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = new ArrayList<Integer>();
        }
        int max = A[0];
        for (int i = 1; i < n; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }
        for (int i = 0; i < n; i++) {
            int index = hash(A[i], max, numberOfBuckets);
            buckets[index].add(A[i]);
        }   
        for (int i = 0; i < numberOfBuckets; i++) {
            Collections.sort(buckets[i]);
        }   
        int index = 0;
        for (int i = 0; i < numberOfBuckets; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                A[index++] = buckets[i].get(j);
            }
        }
    }  
    public static int hash(int i, int max, int numberOfBuckets) {
        return (int) Math.floor(i / (double) max * (numberOfBuckets - 1));
    }
}
