package cortes.luis;

public interface Algorithm {
    enum Type {
        CLASSICAL, DIVIDE_AND_CONQUER, STRASSEN;
    }
    Type type = null;
    public int[][] multiply(int[][] a, int[][] b);

    public Type getType();
}
