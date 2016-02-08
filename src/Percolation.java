import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by yzadorozhnyy on 08.02.2016.
 */
public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF w;
    private int size;
    private int top;
    private int bottom;

    public Percolation(int N) {
        validate(N);
        size = N;
        grid = new boolean[size][size];
        w = new WeightedQuickUnionUF(N * N + 2);
        bottom = size * size + 1;
    }

    private int xyTo1D(int i, int j) {
        validate(i, j);
        return size * (i - 1) + j;
    }

    private void validate(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("N should be more that 0");
        }
    }

    private void validate(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size) {
            throw new IndexOutOfBoundsException("Element [" + i + ", " + j + "] is out of gird");
        }
    }

    public void open(int i, int j) {
        validate(i, j);
        grid[i - 1][j - 1] = true;

        if (i == 1) {
            w.union(xyTo1D(i, j), top);
        }

        if (i == size) {
            w.union(bottom, xyTo1D(i, j));
        }

        if (i > 1 && isOpen(i - 1, j)) {
            w.union(xyTo1D(i, j), xyTo1D(i - 1, j));
        }

        if (i < size && isOpen(i + 1, j)) {
            w.union(xyTo1D(i, j), xyTo1D(i + 1, j));
        }

        if (j > 1 && isOpen(i, j - 1)) {
            w.union(xyTo1D(i, j), xyTo1D(i, j - 1));
        }

        if (j < size && isOpen(i, j + 1)) {
            w.union(xyTo1D(i, j), xyTo1D(i, j + 1));
        }
    }

    public boolean isOpen(int i, int j) {
        validate(i, j);
        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        validate(i, j);
        return w.connected(top, xyTo1D(i, j));
    }

    public boolean percolates() {
        return w.connected(top, bottom);
    }

    public static void main(String[] args) {
//        int N = 10;
//        Percolation p = new Percolation(N);


//        if (1 == p.xyTo1D(1, 1)) {
//            System.out.println("true");
//        }
//        if (N * 1 + 1 == p.xyTo1D(2, 1)) {
//            System.out.println("true");
//        }
//        if (11 == p.xyTo1D(3, 3)) {
//            System.out.println("true");
//        }

//        p.open(1, 1);
//        p.open(1, 2);
//
//        System.out.println(p.w.connected(p.xyTo1D(1,1), p.xyTo1D(1, 2)));
    }
}
