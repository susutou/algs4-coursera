package com.susutou.algorithm.algs4.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author susen
 */
public class PointSET {
    private TreeSet<Point2D> set;

    /**
     * construct an empty set of points
     */
    public PointSET() {
        set = new TreeSet<>();
    }

    /**
     * @return is the set empty?
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * @return number of points in the set
     */
    public int size() {
        return set.size();
    }

    /**
     * add the point to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        checkNull(p, "Parameter p cannot be null.");
        set.add(p);
    }

    /**
     * @return does the set contain point p?
     */
    public boolean contains(Point2D p) {
        checkNull(p, "Parameter p cannot be null.");
        return set.contains(p);
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        for (Point2D p : set) {
            StdDraw.point(p.x(), p.y());
        }
    }

    /**
     * @return all points that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        checkNull(rect, "Parameter rect cannot be null.");
        return set.stream()
                .filter(p -> p.x() >= rect.xmin() && p.x() <= rect.xmax() && p.y() >= rect.ymin() && p.y() <= rect.ymax())
                .collect(Collectors.toList());
    }

    /**
     * @return a nearest neighbor in the set to point p; null if the set is empty
     */
    public Point2D nearest(Point2D p) {
        checkNull(p, "Parameter p cannot be null.");

        Optional<Point2D> nearestPoint = set.stream().min((p1, p2) -> {
            double diff = p.distanceSquaredTo(p1) - p.distanceSquaredTo(p2);
            if (diff > 0) {
                return +1;
            } else if (diff < 0) {
                return -1;
            } else {
                return 0;
            }
        });

        if (nearestPoint.isPresent()) {
            return nearestPoint.get();
        } else {
            return null;
        }
    }

    private void checkNull(Object o, String msg) {
        if (o == null) {
            throw new NullPointerException(msg);
        }
    }

    /**
     * unit testing of the methods (optional)
     */
    public static void main(String[] args) {

    }
}
