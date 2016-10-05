/**
 * Created by Olivi on 29-09-2016.
 */

//MaxValue er en subclass til thread
public class MaxValue extends Thread {
    private int lo, hi;
    private int[] arr;
    private int ans = 0;

    public MaxValue(int[] arr, int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
    }

    @Override
    //Når jeg kører thread'en
    public void run() {
        int max = 0;
        for (int i = lo; i < hi; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        ans = max;
    }

    /**
     * Sum the elements of an array.
     *
     * @param arr array to sum
     * @return sum of the array's elements
     * @throws InterruptedException shouldn't happen
     */
    public static int sum(int[] arr) throws InterruptedException {
        int len = arr.length;
        int ans = 0;

        // Create and start 4 threads.
        MaxValue[] ts = new MaxValue[4];
        for (int i = 0; i < 4; i++) {
            //først fra 0-25, så 25-50, 50-75, 75-100
            ts[i] = new MaxValue(arr, (i * len) / 4, ((i + 1) * len / 4));
            ts[i].start();
        }

        int max = 0;
        // Wait for the threads to finish and sum their results.
        for (int i = 0; i < 4; i++) {
            ts[i].join(); //venter på den næste thread
            if (ts[i].ans > max) {
                max = ts[i].ans;
            }
        }
        ans = max;
        return ans;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        int sum = sum(arr);
        System.out.println("Max: " + sum);
    }
}
