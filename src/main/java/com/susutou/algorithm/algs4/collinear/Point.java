package com.susutou.algorithm.algs4.collinear;

import edu.princeton.cs.introcs.StdDraw;

import java.util.Comparator;

/**
 * @author susen
 */
public class Point implements Comparable<Point> {
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        public int compare(Point o1, Point o2) {
            double s1 = Point.this.slopeTo(o1);
            double s2 = Point.this.slopeTo(o2);
            return s1 > s2 ? 1 : s1 < s2 ? -1 : o1.compareTo(o2);
        }
    };
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {

    }

    public void draw() {
        StdDraw.point(this.x, this.y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if ((this.x == that.x) && (this.y == that.y))
            return Double.NEGATIVE_INFINITY;
        if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        if (this.y == that.y) {
            return 0.0D;
        }
        return (that.y - this.y) / (that.x - this.x);
    }

    public int compareTo(Point that) {
        if (this.y > that.y)
            return 1;
        if (this.y < that.y) {
            return -1;
        }
        return this.x == that.x ? 0 : this.x > that.x ? 1 : -1;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
