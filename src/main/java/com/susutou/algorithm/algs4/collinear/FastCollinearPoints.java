package com.susutou.algorithm.algs4.collinear;

import java.util.Arrays;

/**
 * @author susen
 */
public class FastCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        int n = points.length;
        int segmentId = 0;
        LineSegment[] allSegments = new LineSegment[n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
                            Point[] tuple = new Point[] {p, q, r, s};
                            Arrays.sort(tuple);
                            allSegments[segmentId] = new LineSegment(tuple[0], tuple[3]);
                            segmentId++;
                        }
                    }
                }
            }
        }

        segments = new LineSegment[segmentId];
        System.arraycopy(allSegments, 0, segments, 0, segmentId);
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }

//    public static void main(String[] args) {
//        // rescale coordinates and turn on animation mode
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//
//        String filename = args[0];
//        In in = new In(filename);
//
//        int n = in.readInt();
//        Point[] points = new Point[n];
//
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//            points[i].draw();
//        }
//
//        Arrays.sort(points);
//
//        for (int i = 0; i < n - 4; i++) {
//            Point origin = points[i];
//            Arrays.sort(points, i + 1, n, origin.slopeOrder());
//            int j = i + 1;
//            double slope = origin.slopeTo(points[j]);
//            int continuityCounter = 1;
//            while (true) {
//                while (++j < n && origin.slopeTo(points[j]) == slope) {
//                    continuityCounter++;
//                }
//                if (continuityCounter >= 3) {
//                    Point[] segment = new Point[continuityCounter + 1];
//                    segment[0] = origin;
//                    System.arraycopy(
//                            points, j - continuityCounter,
//                            segment, j - continuityCounter - (j - continuityCounter) + 1,
//                            j - (j - continuityCounter));
//                    Arrays.sort(segment);
//
//                    segment[0].drawTo(segment[segment.length - 1]);
//                    for (int k = 0; k < segment.length; k++) {
//                        if (k == 0) {
//                            StdOut.print(segment[k]);
//                        } else {
//                            StdOut.print(" -> " + segment[k]);
//                        }
//                    }
//                    StdOut.println();
//                }
//                if (j + 1 < n) {
//                    slope = origin.slopeTo(points[j]);
//                    continuityCounter = 1;
//                } else {
//                    break;
//                }
//            }
//        }
//
//        // display to screen all at once
//        StdDraw.show(0);
//
//        // reset the pen radius
//        StdDraw.setPenRadius();
//    }
}
