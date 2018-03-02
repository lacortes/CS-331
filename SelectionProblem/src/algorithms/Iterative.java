package algorithms;

public class Iterative extends QuickSort {
    protected int perform(int k) {
        int m = 0;
        int j = numbers.length - 1;

        while (true) {
            partition(m, j);
            if (k == pivot)
                return numbers[k];
            else if (k < pivot)
                j = pivot -1;
            else {
                m = pivot + 1;
                k = k - pivot;
            }
        }
    }
}
