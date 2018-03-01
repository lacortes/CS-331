import algorithms.Algorithm;
import algorithms.MergeSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] test = {27, 10, 12, 20, 25, 13, 15, 22};

        Algorithm mergesort = new MergeSort();
        int[] answer = mergesort.solve(test, 1);

        Arrays.stream(answer).forEach(x -> System.out.print(x + " "));
        System.out.println();

    }
}