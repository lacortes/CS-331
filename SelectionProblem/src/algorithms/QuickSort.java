package algorithms;

public abstract class QuickSort implements Algorithm {
    protected int[] numbers;
    protected int pivot;

    @Override
    public int solve(int[] numbers, int kth) {
        this.numbers = numbers;
        this.pivot = 0;

        return perform(kth - 1);
    }

    protected void partition(int low, int high) {
        int pivotItem = numbers[low];
        int j = low;

        for (int i = low + 1; i <= high; i++) {
            if (numbers[i] < pivotItem) {
                j++;
                exchange(i, j);
            }
        }
        pivot = j;
        exchange(low, pivot);
    }

    protected void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    protected abstract int perform(int k);
}