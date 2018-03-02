package algorithms;

import java.util.Arrays;

public class Recursive implements QuickSort {
    private int[] numbers ;
    private int pivot;

    @Override
    public int solve(int[] numbers, int kth) {
        this.numbers = numbers;
        this.pivot = 0;

        return perform(kth - 1);
    }

    private int perform(int k) {
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

    private void partition(int low, int high) {
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

    private void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

}
