package algorithms;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort implements Algorithm {
    private int[] numbers;
    private int[] local;

    @Override
    public int solve(int[] numbers, int kth) {
        this.numbers = numbers;
        int length = numbers.length;

        this.local = new int[length];
        mergesort(0, length - 1);

        Arrays.stream(numbers).forEach(x -> System.out.print(" "+x));
        System.out.println();

        return local[kth -1];
    }

    private void mergesort(int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            mergesort(low,  middle);
            mergesort(middle + 1, high);
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            local[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high) {
            if (local[i] <= local[j]) {
                numbers[k] = local[i];
                i++;
            } else {
                numbers[k] = local[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            numbers[k] = local[i];
            k++;
            i++;
        }
    }
}