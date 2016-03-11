import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        checkPoints(points);

        List<LineSegment> found = new ArrayList<>();
        Point[] copy = Arrays.copyOf(points, points.length);
        Arrays.sort(copy);

        Point p, q, r, s;
        double slopePQ, slopePR, slopePS;
        for (int i = 0; i < copy.length; i++) {
            for (int j = i + 1; j < copy.length; j++) {
                if (copy[i].compareTo(copy[j]) == 0) {
                    continue;
                }
                for (int k = j + 1; k < copy.length; k++) {
                    if (copy[i].compareTo(copy[k]) == 0 || copy[j].compareTo(copy[k]) == 0) {
                        continue;
                    } else if (copy[i].slopeTo(copy[j]) !=  copy[i].slopeTo(copy[k])) {
                        continue;
                    }
                    for (int l = k + 1; l < copy.length; l++) {
                        if (copy[i].compareTo(copy[k]) == 0 || copy[j].compareTo(copy[k]) == 0 || copy[k].compareTo(copy[k]) == 0) {
                            continue;
                        } else if (copy[i].slopeTo(copy[k]) ==  copy[i].slopeTo(copy[k])) {
                            found.add(new LineSegment(copy[i], copy[k]));
                        }
                    }
                }
            }
        }

        segments = found.toArray(new LineSegment[found.size()]);
    }

    public static void main(String[] args) {
        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }

    private void checkPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("Array is null");
        }

        for (Point point : points) {
            if (point == null) {
                throw new NullPointerException("Array element is null");
            }
        }

        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i - 1].compareTo(points[i]) == 0) {
                throw new IllegalArgumentException("There are a repeated point");
            }
            ;
        }
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
    }
}
