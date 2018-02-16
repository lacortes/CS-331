package cortes.luis;

public class Strassen implements Algorithm {
    Algorithm classical = new Classical();

    @Override
    public Type getType() {
        return Type.STRASSEN;
    }

    @Override
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = strassen(a.length, a, b);
        return c;
    }

    private int[][] strassen(int n, int[][] a, int[][] b) {
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

            // dividing the matrices in 4 sub-matrices:
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

            int[][] m1 = strassen(n/2, add(a11, a22), add(b11, b22) );
            int[][] m2 = strassen(n/2, add(a21, a22), b11);
            int[][] m3 = strassen(n/2, a11, subtract(b12, b22));
            int[][] m4 = strassen(n/2, a22, subtract(b21, b11));
            int[][] m5 = strassen(n/2, add(a11, a12), b22);
            int[][] m6 = strassen(n/2, subtract(a21, a11), add(b11, b12));
            int[][] m7 = strassen(n/2, subtract(a12, a22), add(b21, b22));

            // Calculate C
            int[][] c11 = add(subtract(add(m1, m4), m5), m7);
            int[][] c12 = add(m3, m5);
            int[][] c21 = add(m2, m4);
            int[][] c22 = add(subtract( add(m1, m3), m2), m6);

            int[][] c = new int[n][n];

            // Combine submatrices to form C
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

    private int[][] subtract(int[][] a, int[][] b) {
        return MatrixUtil.subtract(a, b);
    }

    private int[][] add(int[][] a, int[][] b) {
        return MatrixUtil.add(a, b);
    }

}
