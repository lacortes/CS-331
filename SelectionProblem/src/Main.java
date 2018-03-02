import algorithms.*;

public class Main {
    public static void main(String[] args) {
        int[] test = {27, 10, 12, 20, 25, 13, 15, 22};
        int[] test2 = {5, 4, 15, 100, 2, 53, 99, 13, 10};


        Algorithm iterative = new Iterative();
        Algorithm recursive = new Recursive();
        System.out.println(iterative.solve(test, 2));
        System.out.println(recursive.solve(test, 2));



    }
}