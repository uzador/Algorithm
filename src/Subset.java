import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

/**
 * Created by yzadorozhnyy on 11.02.2016.
 */
public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        int k = Integer.parseInt(args[0]);

        while (StdIn.hasNextLine() && !StdIn.isEmpty()) {
            System.out.println(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            System.out.println(rq.dequeue());
        }
    }
}
