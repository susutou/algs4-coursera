package com.susutou.algorithm.algs4.collinear;

import java.util.Arrays;

/**
 * @author susen
 */
public class FastCollinearPoints {
    private Object[] segmentObjects;
    private LineSegment[] segments;
    private int segmentId;

    private class Pair {
        public Pair(int x, int y, double slope) {

        }
    }

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        checkNull(points);
        checkDuplicate(points);

        int n = points.length;
        Point[] pointsCopy = new Point[n];
        System.arraycopy(points, 0, pointsCopy, 0, n);
        Arrays.sort(pointsCopy);

        segmentId = 0;
        segmentObjects = new LineSegment[n];

        for (int i = 0; i < n - 3; i++) {
            Point p = pointsCopy[i];
            Point[] sortedPoints = new Point[n];  // content length = n - 1, last = null
            System.arraycopy(pointsCopy, 0, sortedPoints, 0, i);
            System.arraycopy(pointsCopy, i + 1, sortedPoints, i, n - i - 1);
            Arrays.sort(sortedPoints, 0, n - 1, p.slopeOrder());

            int currentStartIndex = -1;
            int j = 0;

            while (j < n) {
                if (currentStartIndex == -1) {
                    currentStartIndex = j;
                } else {
                    Point currentStartPoint = sortedPoints[currentStartIndex];
                    Point currentPoint = sortedPoints[j];
                    if (currentPoint == null || currentPoint.slopeTo(p) != currentStartPoint.slopeTo(p)) {
                        int len = j - currentStartIndex;
                        /* critical - if current starting point of this segment is smaller than p,
                           then this line segment is already in the collection */
                        if (len >= 3 && currentStartPoint.compareTo(p) > 0) {
                            segmentObjects[segmentId] = new LineSegment(p, sortedPoints[j - 1]);
                            segmentId++;
                            if (segmentId == segmentObjects.length) {
                                resize(segmentId * 2);
                            }
                        }
                        currentStartIndex = j;
                    }
                }
                j++;
            }
        }

        segments = new LineSegment[segmentId];

        for (int i = 0; i < segmentId; i++) {
            segments[i] = (LineSegment) segmentObjects[i];
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] ret = new LineSegment[numberOfSegments()];
        System.arraycopy(segments, 0, ret, 0, numberOfSegments());

        return ret;
    }

    private void checkDuplicate(Point[] points) {
        if (points.length > 0) {
            Point[] pointsCopy = new Point[points.length];
            System.arraycopy(points, 0, pointsCopy, 0, points.length);
            Arrays.sort(pointsCopy);
            Point currentPoint = pointsCopy[0];
            for (int i = 1; i < pointsCopy.length; i++) {
                if (pointsCopy[i].compareTo(currentPoint) == 0) {
                    throw new IllegalArgumentException("Cannot have duplicate points.");
                } else {
                    currentPoint = pointsCopy[i];
                }
            }
        }
    }

    private void checkNull(Point[] points) {
        if (points == null) {
            throw new NullPointerException("The Point array cannot be null.");
        } else {
            for (Point p : points) {
                if (p == null) {
                    throw new NullPointerException("One of the points is null.");
                }
            }
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
