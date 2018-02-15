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
        long start = System.currentTimeMillis();
        this.c = algorithm.multiply(a, b);
        this.elapsedTime = System.currentTimeMillis() - start;
    }

    public int[][] getResult() {
        return this.c;
    }

    public int getDimensions() {
        return this.a.length;
    }

    public float getElapsedSeconds() {
        return this.elapsedTime / 1000F;
    }

    public long getElapsedTimeMili() {
        return this.elapsedTime;
    }

    public Algorithm.Type getType() {
        return algorithm.getType();
    }
}
