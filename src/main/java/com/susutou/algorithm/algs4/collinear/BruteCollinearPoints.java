package com.susutou.algorithm.algs4.collinear;

import java.util.Arrays;

/**
 * @author susen
 */

/**
 * @author susen
 */
public class BruteCollinearPoints {
    private Object[] segmentObjects;
    private LineSegment[] segments;
    private int segmentId;

    // finds all line segmentObjects containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (checkDuplicatePoints(points)) {
            throw new IllegalArgumentException("Cannot have duplicate points.");
        }

        int n = points.length;
        segmentId = 0;
        segmentObjects = new LineSegment[n];

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
                            segmentObjects[segmentId] = new LineSegment(tuple[0], tuple[3]);
                            segmentId++;
                            if (segmentId == segmentObjects.length) {
                                resize(segmentId * 2);
                            }
                        }
                    }
                }
            }
        }

        segments = new LineSegment[segmentId];
        for (int i = 0; i < segmentId; i++) {
            segments[i] = (LineSegment) segmentObjects[i];
        }
    }

    // the number of line segmentObjects
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segmentObjects
    public LineSegment[] segments() {
        LineSegment[] ret = new LineSegment[numberOfSegments()];
        System.arraycopy(segments, 0, ret, 0, numberOfSegments());

        return ret;
    }

    private boolean checkDuplicatePoints(Point[] points) {
        if (points.length > 0) {
            Point[] pointsCopy = new Point[points.length];
            System.arraycopy(points, 0, pointsCopy, 0, points.length);
            Arrays.sort(pointsCopy);
            Point currentPoint = pointsCopy[0];
            for (int i = 1; i < pointsCopy.length; i++) {
                if (pointsCopy[i].compareTo(currentPoint) == 0) {
                    return true;
                } else {
                    currentPoint = pointsCopy[i];
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private void resize(int capacity) {
        assert capacity >= segmentId;
        if (capacity > segmentId) {
            Object[] temp = new Object[capacity];
            System.arraycopy(segmentObjects, 0, temp, 0, segmentId);
            segmentObjects = temp;
        }
    }
}