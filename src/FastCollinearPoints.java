import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private static LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        checkPoints(points);

        List<LineSegment> found = new ArrayList<>();
        int N = points.length;

        Point[] copy = new Point[N];
        for (int j = 0; j < N; j++)
            copy[j] = points[j];
        // For each point in the array, sort the rest of the array with that point as the base
        // Then, iterate through the rest of the array to find collinear points
        for (int i = 0; i < N; i++) {
            Point base = copy[i];
            // Sort the array by the base
            Arrays.sort(points, 0, N, base.slopeOrder());
            int lo = 1;
            int hi = 2;
            // base must be smaller than every point on its line (so that we don't get permutations)
            boolean flag = base.compareTo(points[lo]) < 0 ? true : false;
            // For the rest of the array, find the collinear points
            while (hi < N) {
                // If the current point has the same slope as the previously found point:
                // Make sure it's larger than the base point.
                if (points[hi].slopeTo(base) == points[lo].slopeTo(base)) {
                    if (points[hi].compareTo(base) < 0)
                        flag = false;
                }
                // If the current point has a different slope than the previously found point:
                else {
                    // If we found at least 3 collinear points (excluding base):
                    // Handle collinear points
                    // Note: collinear points will be at points[lo], points[lo+1], ..., points[hi-1]
                    // Note: number of collinear points found is hi-lo
                    if (flag && hi - lo >= 3) {
                        Arrays.sort(points, lo, hi);
                        found.add(new LineSegment(points[0], points[hi - 1]));
//                        Fast.handleCollinear(points, lo, hi);
                    }
                    // Reset lo, flag
                    lo = hi;
                    flag = base.compareTo(points[lo]) < 0 ? true : false;
                }
                hi++;
            }
            // Check edge case (if the last point has the same slope as the previously found point
            if (points[N - 1].slopeTo(base) == points[lo].slopeTo(base)) {
                if (flag && hi - lo >= 3) {
//                    Fast.handleCollinear(points, lo, hi);
                    Arrays.sort(points, lo, hi);
                    found.add(new LineSegment(points[0], points[hi - 1]));
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
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
        }
    }
}
