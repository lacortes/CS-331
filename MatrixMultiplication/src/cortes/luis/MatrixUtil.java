package cortes.luis;

public class MatrixUtil {
    public static int[][] partition(int[][] subMatrix, int rowStart, int rowEnd, int colStart, int colEnd) {
        int partRowIndex = 0;
        int partColIndex = 0;
        int dimension = subMatrix.length / 2;
        int[][] partitioned = new int[dimension][dimension];
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = colStart; j < colEnd; j++) {
                partColIndex %= dimension;
                partitioned[partRowIndex][partColIndex++] = subMatrix[i][j];
            }
            partRowIndex++;
        }
        return partitioned;
    }

    public static int[][] join(int[][] c, int[][] partition, int rowStart, int rowEnd, int colStart, int colEnd) {
        int partRowIndex = 0;
        int partColIndex = 0;
        int dimension = c.length / 2;
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = colStart; j < colEnd; j++) {
                partColIndex %= dimension;
                c[i][j] = partition[partRowIndex][partColIndex++];
            }
            partRowIndex++;
        }
        return c;
    }

    public static int[][] subtract(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] - b[i][j];
        return c;
    }

    public static int[][] add(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }
}
