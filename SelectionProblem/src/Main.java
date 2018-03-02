import algorithms.Algorithm;
import algorithms.MergeSort;
import algorithms.Recursive;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] test = {27, 10, 12, 20, 25, 13, 15, 22};
        int[] test2 = {5, 15, 100, 2, 53, 99, 13, 10};

//        Algorithm mergesort = new MergeSort();
//        int answer = mergesort.solve(test, 1);

        Algorithm recursive = new Recursive();
        System.out.println(recursive.solve(test2, 2));


    }
}