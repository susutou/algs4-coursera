package com.susutou.algorithm.algs4.collinear;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {
    private static final double DELTA = 1.0E-10D;

    @Test
    public void testSlopeTo()
            throws Exception {
        Point topLeft = new Point(-1, 1);
        Point topRight = new Point(1, 1);
        Point bottomLeft = new Point(-1, -1);
        Point bottomRight = new Point(1, -1);

        Assert.assertEquals(-1.0D, topLeft.slopeTo(bottomRight), DELTA);
        Assert.assertEquals(1.0D, bottomLeft.slopeTo(topRight), DELTA);
        Assert.assertEquals(0.0D, topLeft.slopeTo(topRight), DELTA);
        Assert.assertEquals(Double.POSITIVE_INFINITY, bottomLeft.slopeTo(topLeft), DELTA);
        Assert.assertEquals(Double.NEGATIVE_INFINITY, topRight.slopeTo(bottomRight), DELTA);
    }

    @Test
    public void testCompareTo() throws Exception {
        Point p0 = new Point(10, 10);
        Point p1 = new Point(9, 10);
        Point p2 = new Point(10, 9);
        Point p3 = new Point(10, 11);

        Assert.assertEquals(0L, p0.compareTo(p0));
        Assert.assertEquals(1L, p0.compareTo(p1));
        Assert.assertEquals(-1L, p1.compareTo(p0));
        Assert.assertEquals(1L, p0.compareTo(p2));
        Assert.assertEquals(-1L, p0.compareTo(p3));
    }

    @Test
    public void testToString() throws Exception {
        Point p = new Point(3, 4);
        Assert.assertEquals("(3, 4)", p.toString());
    }
}