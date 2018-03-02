package algorithms;

public class Recursive extends QuickSort {
    private int kth;
    @Override
    protected int perform(int k) {
        this.kth = k;
        quicksort(0, numbers.length-1);
        return numbers[kth];
    }

    private void quicksort(int low, int high) {
        if (high > low) {
            partition(low, high);

            if (kth == pivot) {
                return;
            }
            quicksort(low, pivot-1);
            quicksort(pivot + 1, high);
        }
    }
}
