package cortes.luis;

public class DivideAndConquer implements Algorithm {
    Algorithm classical = new Classical();

    @Override
    public Type getType() {
        return Type.DIVIDE_AND_CONQUER;
    }

    @Override
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = divideAndConquer(a.length, a, b);
        return c;
    }

    private int[][] divideAndConquer(int n, int[][] a, int[][] b) {
        if (n <= 2) {
            return classical.multiply(a, b);
        } else {
            // Partition A into four submatrices
            int[][] a11 = partition(a, 0, n/2, 0, n/2);
            int[][] a12 = partition(a, 0, n/2, n/2, n);
            int[][] a21 = partition(a, n/2, n, 0, n/2);
            int[][] a22 = partition(a, n/2, n, n/2, n);

            // Partition B into four submatrices
            int[][] b11 = partition(b, 0, n/2, 0, n/2);
            int[][] b12 = partition(b, 0, n/2, n/2, n);
            int[][] b21 = partition(b, n/2, n, 0, n/2);
            int[][] b22 = partition(b, n/2, n, n/2, n);

            // x1 ← a11 * b11
            // x2 ← a12 * b21
            // x3 ← a11 * b12
            // x4 ← a12 * b22
            // x5 ← a21 * b11
            // x6 ← a22 * b21
            // x7 ← a21 * b12
            // x8 ← a11 * b22
            int[][] x1 = divideAndConquer(n/2, a11, b11);
            int[][] x2 = divideAndConquer(n/2, a12, b21);
            int[][] x3 = divideAndConquer(n/2, a11, b12);
            int[][] x4 = divideAndConquer(n/2, a12, b22);
            int[][] x5 = divideAndConquer(n/2, a21, b11);
            int[][] x6 = divideAndConquer(n/2, a22, b21);
            int[][] x7 = divideAndConquer(n/2, a21, b12);
            int[][] x8 = divideAndConquer(n/2, a22, b22);

            // C11 ← X1 + X2
            // C12 ← X3 + X4
            // C21 ← X5 + X6
            // C22 ← X7 + X8
            int[][] c11 = add(x1, x2);
            int[][] c12 = add(x3, x4);
            int[][] c21 = add(x5, x6);
            int[][] c22 = add(x7, x8);

            int[][] c  = new int[n][n];
            c = join(c, c11, 0, n/2, 0, n/2);
            c = join(c, c12, 0, n/2, n/2, n);
            c = join(c, c21, n/2, n, 0, n/2);
            c = join(c, c22, n/2, n, n/2, n);
            return c;

        }
    }

    private int[][] partition(int[][] subMatrix, int rowStart, int rowEnd, int colStart, int colEnd) {
        return MatrixUtil.partition(subMatrix, rowStart, rowEnd, colStart, colEnd);
    }

    private int[][] join(int[][] c, int[][] partition, int rowStart, int rowEnd, int colStart, int colEnd) {
        return MatrixUtil.join(c, partition, rowStart, rowEnd, colStart, colEnd);
    }

    private int[][] add(int[][] a, int[][] b) {
        return MatrixUtil.add(a, b);
    }
}
