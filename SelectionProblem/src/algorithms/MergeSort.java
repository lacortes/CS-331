package algorithms;

import java.util.Arrays;

public class MergeSort implements Algorithm {
    private int[] S;


    public int[] solve(int[] numbers, int kth) {
        this.S = numbers;
        mergesort(0, S.length -1);
        return numbers;
    }

    private void mergesort(int low, int high) {
        int mid;

        if (low < high) {
            mid = (low + high) / 2;
            mergesort(low, mid);
            mergesort(mid+1, high);
            merge(low, mid, high);
        }
    }

    private void merge(int low, int mid, int high) {
        int i, j, k;
        int[] local = new int[this.S.length];
        i = low;
        j = mid + 1;
        k = low;

        while (i <= mid && j <= high) {
            System.out.println("Checking: "+this.S[i]+"   "+this.S[j]);
            if (this.S[i] <  this.S[j]) {
                local[k] = this.S[i];
                i++;
            } else {
                local[k] = this.S[j];
                j++;
            }
            k++;
        }
        Arrays.stream(local).forEach(x->System.out.print(" "+x));
        System.out.println();

        if (i > mid)
            move(this.S, local, j, high, k);
        else
            move(this.S, local, i, mid, k);
        move(local, this.S, low, high, low);
    }

    private void move(int[] from, int[] to, int fromStart, int fromEnd, int toStart) {
        System.out.println("From: "+fromStart+" to "+fromEnd);
        Arrays.stream(from).forEach(x -> System.out.print(" "+x));
        System.out.println();
        System.out.println("To: ");
        Arrays.stream(to).forEach(x -> System.out.print(" "+x));
        System.out.println();
        while (fromStart < fromEnd) {
            System.out.println("FROM: "+from[fromStart]);
            to[toStart++] = from[fromStart++];
        }
        System.out.println("To: ");
        Arrays.stream(to).forEach(x -> System.out.print(" "+x));
        System.out.println();
    }


}