package cortes.luis;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final int ALG_TIMES = 10;
        final int DIM_MAX = 10;  // 2 ** (DIM_MAX)

        Path path = Paths.get("output.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("nxn,iterations,Classical,Strassen,Divide and Conquer\n");

            int size = 2;
            for (int i = 0; i < DIM_MAX; i++) {
                long meanTimeClassical = 0;
                long meanTimeStrassen = 0;
                long meanTimeDivide = 0;
                int[][] matrix1 = genRandMatrix(size);
                int[][] matrix2 = genRandMatrix(size);

                System.out.println("Size: " + size);
                for (int j = 0; j < ALG_TIMES; j++) {
                    MultiplyMatrix classical = new MultiplyMatrix(new Classical(), matrix1, matrix2);
                    classical.run();

                    MultiplyMatrix strassen = new MultiplyMatrix(new Strassen(), matrix1, matrix2);
                    strassen.run();

                    MultiplyMatrix divide = new MultiplyMatrix(new DivideAndConquer(), matrix1, matrix2);
                    divide.run();


                    meanTimeClassical += classical.getElapsedMicro();
                    meanTimeStrassen += strassen.getElapsedMicro();
                    meanTimeDivide += divide.getElapsedMicro();

                    System.out.println("\tRun: " + (j + 1));
                    displayResult(classical.getType(), classical.getElapsedMicro());
                    displayResult(strassen.getType(), strassen.getElapsedMicro());
                    displayResult(divide.getType(), divide.getElapsedMicro());
                }

                // Get Mean of times
                meanTimeClassical /= ALG_TIMES;
                meanTimeStrassen /= ALG_TIMES;
                meanTimeDivide /= ALG_TIMES;

                writer.write(size+","+ALG_TIMES+","
                        +meanTimeClassical+","+meanTimeStrassen+","+meanTimeDivide+"\n");

                System.out.println("\tClassical Mean Time: " + meanTimeClassical);
                System.out.println("\tStrassen Mean Time: " + meanTimeStrassen);
                System.out.println("\tDivide & Conq Mean Time: " + meanTimeDivide);
                System.out.println("*******************************************************");
                size *= 2;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void displayResult(Algorithm.Type type, long time) {
        System.out.print("\t\tAlg: "+type);
        System.out.println("\t\tTime: "+time);
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
