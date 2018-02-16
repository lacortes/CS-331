package cortes.luis;

public class MultiplyMatrix {
    private Algorithm algorithm;
    private long elapsedTime;
    private int[][] a;
    private int[][] b;
    private int[][] c;

    public MultiplyMatrix(Algorithm algorithm, int[][] a, int[][] b) {
        this.algorithm = algorithm;
        this.a = a;
        this.b = b;
    }

    public void run() {
        long start = System.nanoTime();
        this.c = algorithm.multiply(a, b);
        this.elapsedTime = System.nanoTime() - start;
    }

    public int[][] getResult() {
        return this.c;
    }

    public int getDimensions() {
        return this.a.length;
    }

    public float getElapsedNano() {
        return this.elapsedTime;
    }

    public long getElapsedMicro() {
        return this.elapsedTime / 1000L;
    }


    public Algorithm.Type getType() {
        return algorithm.getType();
    }
}
