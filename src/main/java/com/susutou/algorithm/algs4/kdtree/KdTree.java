package com.susutou.algorithm.algs4.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;

/**
 * @author susen
 */
public class KdTree {
    /**
     * construct an empty set of points
     */
    public KdTree() {

    }

    /**
     * @return is the set empty?
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * @return number of points in the set
     */
    public int size() {
        return 0;
    }

    /**
     * add the point to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {

    }

    /**
     * @return does the set contain point p?
     */
    public boolean contains(Point2D p) {
        return false;
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {

    }

    /**
     * @return all points that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        return new LinkedList<>();
    }

    /**
     * @return a nearest neighbor in the set to point p; null if the set is empty
     */
    public Point2D nearest(Point2D p) {
        return new Point2D(0.5, 0.5);
    }

    /**
     * unit testing of the methods (optional)
     */
    public static void main(String[] args) {

    }
}
