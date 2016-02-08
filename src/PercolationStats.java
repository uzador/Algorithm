import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by yzadorozhnyy on 08.02.2016.
 */
public class PercolationStats {
    private int size;
    private int repeat;
    private double[] results;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("size and repeat should be more than 0");
        }

        this.size = N;
        this.repeat = T;
        results = new double[T];

        Percolation p;
        int count;
        for (int i = 0; i < repeat; i++) {
            p = new Percolation(size);
            count = 0;
            while (!p.percolates()) {
                int f = StdRandom.uniform(1, size + 1);
                int s = StdRandom.uniform(1, size + 1);
                if (!p.isOpen(f, s)) {
                    p.open(f, s);
                    count++;
                }
            }
            results[i] = ((double) count)/(size * size);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddevp(results);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev())/Math.sqrt(repeat));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev())/Math.sqrt(repeat));
    }

    public static void main(String[] args) {
        int N = -1;
        int T = -1;

        if (args[0] != null && args[1] != null) {
            N = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
        }

        PercolationStats ps = new PercolationStats(N, T);

        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
