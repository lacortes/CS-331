package cortes.luis;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        long totalTimeClassical = 0;
        long totalTimeStrassen = 0;
        long totalTimeDivide = 0;

        int size = 2;
        for (int i = 0; i < 10; i++) {
            int[][] matrix1 = genRandMatrix(size);
            int[][] matrix2 = genRandMatrix(size);

            MultiplyMatrix classical = new MultiplyMatrix(new Classical(), matrix1, matrix2);
            classical.run();

            MultiplyMatrix strassen = new MultiplyMatrix(new Strassen(), matrix1, matrix2);
            strassen.run();

            MultiplyMatrix divide = new MultiplyMatrix(new DivideAndConquer(), matrix1, matrix2);
            divide.run();

            System.out.println("Size: "+ size);
            displayResult(classical.getType(), classical.getElapsedTimeMili());
            displayResult(strassen.getType(), strassen.getElapsedTimeMili());
            displayResult(divide.getType(), divide.getElapsedTimeMili());
            size *= 2;
        }


//        ArrayList<MultiplyThread> threads = new ArrayList<>();
//        Stack<MultiplyThread> threadsDone = new Stack<>();
//
//        MultiplyThread classical = new MultiplyThread(new Classical(), m1, m2);
//        threads.add(classical);
//
//        MultiplyThread strassen = new MultiplyThread(new Strassen(), m1, m2);
//        threads.add(strassen);
//
//        classical.start();
//        strassen.start();
//
//        // Wait until all threads are done executing. Add completed threads to stack
//        int count = 0;
//        while (!threads.isEmpty()) {
//            int index = count % threads.size();
//            MultiplyThread thread = threads.get(index);
//
//            // Thread is completed
//            if (thread.getState() == Thread.State.TERMINATED) {
//                threads.remove(index);  // Remove from list of running threads
//                threadsDone.push(thread);  // Add to stack of completed threads
//            }
//            count++;
//        }
//
//        while (!threadsDone.isEmpty()) {
//            MultiplyThread thread = threadsDone.pop();
//            System.out.println("Alg: " + thread.getType());
//            System.out.println("Time: " + thread.getElapsedTimeMili());
////            System.out.println(displayMatrix( thread.getResult() ));
//        }

    }

    public static void displayResult(Algorithm.Type type, long time) {
        System.out.println("\tAlg: "+type);
        System.out.println("\tTime: "+time);
        System.out.println();
    }

    public static String displayMatrix(int[][] matrix) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                string.append(matrix[i][j]);
                string.append(" ");
            }
            string.append("\n");
        }
        return string.toString();
    }

    public static int[][] genRandMatrix(int dimension) {
        int[][] matrix = new int[dimension][dimension];
        Random random = new Random();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                matrix[i][j] = random.nextInt(199)+1;
            }
        }
        return matrix;
    }
}
