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

            int halfSize = n/2;

            int[][] a11 = new int[halfSize][halfSize];
            int[][] a12 = new int[halfSize][halfSize];
            int[][] a21 = new int[halfSize][halfSize];
            int[][] a22 = new int[halfSize][halfSize];

            int[][] b11 = new int[halfSize][halfSize];
            int[][] b12 = new int[halfSize][halfSize];
            int[][] b21 = new int[halfSize][halfSize];
            int[][] b22 = new int[halfSize][halfSize];

            for (int i = 0; i < halfSize; i++) {
                for (int j = 0; j < halfSize; j++) {
                    // Partition A into four submatrices
                    a11[i][j] = a[i][j]; // top left
                    a12[i][j] = a[i][j + halfSize]; // top right
                    a21[i][j] = a[i + halfSize][j]; // bottom left
                    a22[i][j] = a[i + halfSize][j + halfSize]; // bottom right

                    // Partition B into four submatrices
                    b11[i][j] = b[i][j]; // top left
                    b12[i][j] = b[i][j + halfSize]; // top right
                    b21[i][j] = b[i + halfSize][j]; // bottom left
                    b22[i][j] = b[i + halfSize][j + halfSize]; // bottom right
                }
            }

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
            for (int i = 0; i < halfSize; i++) {
                for (int j = 0; j < halfSize; j++) {
                    c[i][j] = c11[i][j];
                    c[i][j + halfSize] = c12[i][j];
                    c[i + halfSize][j] = c21[i][j];
                    c[i + halfSize][j + halfSize] = c22[i][j];
                }
            }
            return c;

        }
    }

    private int[][] add(int[][] a, int[][] b) {
        return MatrixUtil.add(a, b);
    }
}
