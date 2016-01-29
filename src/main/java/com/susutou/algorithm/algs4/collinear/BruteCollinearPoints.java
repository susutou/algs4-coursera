package com.susutou.algorithm.algs4.collinear;

import java.util.Arrays;

/**
 * @author susen
 */

/**
 * @author susen
 */
public class BruteCollinearPoints {
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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

    private void verifyPoints(Point[] points) {

    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }
}