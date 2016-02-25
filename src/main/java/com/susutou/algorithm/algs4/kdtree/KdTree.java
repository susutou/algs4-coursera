package com.susutou.algorithm.algs4.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.List;

/**
 * @author susen
 */
public class KdTree {
    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p, RectHV rect, Node lb, Node rt) {
            this.p = p;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
        }

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
            this.lb = null;
            this.rt = null;
        }
    }

    private static class Pair {
        private Node node;
        private int level;

        public Pair(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    private Node root;          // the root of 2D tree
    private Point2D closest;

    private int size;

    /**
     * construct an empty set of points
     */
    public KdTree() {
        root = null;
        size = 0;
    }

    /**
     * @return is the set empty?
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return number of points in the set
     */
    public int size() {
        return size;
    }

    /**
     * add the point to the set (if it is not already in the set)
     */
    public void insert(Point2D p) {
        checkNull(p, "Parameter p cannot be null.");

        if (root == null) {
            root = new Node(p, new RectHV(0, 0, 1, 1));
            size++;
        } else {
            Pair pair = findParent(p);
            Node parent = pair.node;
            Point2D parentPoint = parent.p;
            RectHV rect = parent.rect;
            int level = pair.level - 1;

            if (!p.equals(parent.p)) {
                if (level % 2 == 0) {
                    if (p.x() <= parentPoint.x()) {
                        parent.lb = new Node(p, new RectHV(rect.xmin(), rect.ymin(), parentPoint.x(), rect.ymax()));
                    } else {
                        parent.rt = new Node(p, new RectHV(parentPoint.x(), rect.ymin(), rect.xmax(), rect.ymax()));
                    }
                } else {
                    if (p.y() <= parentPoint.y()) {
                        parent.lb = new Node(p, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), parentPoint.y()));
                    } else {
                        parent.rt = new Node(p, new RectHV(rect.xmin(), parentPoint.y(), rect.xmax(), rect.ymax()));
                    }
                }
                size++;
            }
        }
    }

    /**
     * @return does the set contain point p?
     */
    public boolean contains(Point2D p) {
        checkNull(p, "Parameter p cannot be null.");

        if (isEmpty()) {
            return false;
        } else {
            Pair pair = findParent(p);
            return p.equals(pair.node.p);
        }
    }

    private Pair findParent(Point2D p) {
        int level = 0;
        Node cursorNode = root;
        Node parent = cursorNode;

        while (cursorNode != null && !p.equals(cursorNode.p)) {
            parent = cursorNode;
            if (level % 2 == 0) {  // left-right partition
                if (p.x() <= cursorNode.p.x()) {
                    cursorNode = cursorNode.lb;
                } else {
                    cursorNode = cursorNode.rt;
                }
            } else {               // top-bottom partition
                if (p.y() <= cursorNode.p.y()) {
                    cursorNode = cursorNode.lb;
                } else {
                    cursorNode = cursorNode.rt;
                }
            }
            level++;
        }

        if (cursorNode == null) {
            return new Pair(parent, level);      // cannot find the point
        } else {
            return new Pair(cursorNode, level);  // found the point
        }
    }

    /**
     * draw all points to standard draw
     */
    public void draw() {
        drawNode(root, 0);
    }

    private void drawNode(Node n, int level) {
        if (n != null) {
            StdDraw.setPenRadius();
            if (level % 2 == 0) {  // vertical, red
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(n.p.x(), n.rect.ymin(), n.p.x(), n.rect.ymax());
            } else {               // horizontal, blue
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(n.rect.xmin(), n.p.y(), n.rect.xmax(), n.p.y());
            }
            StdDraw.setPenRadius(.01);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(n.p.x(), n.p.y());
            drawNode(n.lb, level + 1);
            drawNode(n.rt, level + 1);
        }
    }

    /**
     * @return all points that are inside the rectangle
     */
    public Iterable<Point2D> range(RectHV rect) {
        checkNull(rect, "Parameter rect cannot be null.");

        List<Point2D> points = new LinkedList<>();
        range(root, rect, points);

        return points;
    }

    private void range(Node node, RectHV rect, List<Point2D> points) {
        if (node != null && rect.intersects(node.rect)) {
            if (rect.contains(node.p)) {
                points.add(node.p);
            }
            range(node.lb, rect, points);
            range(node.rt, rect, points);
        }
    }

    /**
     * @return a nearest neighbor in the set to point p; null if the set is empty
     */
    public Point2D nearest(Point2D p) {
        checkNull(p, "Parameter p cannot be null.");

        if (isEmpty()) {
            return null;
        } else {
            closest = root.p;
            nearest(root, p, 0);
            return closest;
        }
    }

    private void nearest(Node node, Point2D p, int level) {
        double closestDistance = closest.distanceSquaredTo(p);
        if (node != null && node.rect.distanceSquaredTo(p) < closestDistance) {
            if (p.distanceSquaredTo(node.p) < closestDistance) {
                closest = node.p;
            }

            if (level % 2 == 0) {
                if (p.x() <= node.p.x()) {
                    nearest(node.lb, p, level + 1);
                    nearest(node.rt, p, level + 1);
                } else {
                    nearest(node.rt, p, level + 1);
                    nearest(node.lb, p, level + 1);
                }
            } else {
                if (p.y() <= node.p.y()) {
                    nearest(node.lb, p, level + 1);
                    nearest(node.rt, p, level + 1);
                } else {
                    nearest(node.rt, p, level + 1);
                    nearest(node.lb, p, level + 1);
                }
            }
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
        KdTree kdTree = new KdTree();
        Point2D p1 = new Point2D(0.7, 0.2);
        Point2D p2 = new Point2D(0.5, 0.4);
        Point2D p3 = new Point2D(0.2, 0.3);
        Point2D p4 = new Point2D(0.4, 0.7);
        Point2D p5 = new Point2D(0.9, 0.6);
        Point2D p6 = new Point2D(0.3, 0.7);

        System.out.println(kdTree.contains(p1));
        kdTree.insert(p1);
        System.out.println(kdTree.size());
        kdTree.insert(p2);
        System.out.println(kdTree.size());
        kdTree.insert(p3);
        System.out.println(kdTree.size());
        kdTree.insert(p4);
        System.out.println(kdTree.size());
        kdTree.insert(p3);
        System.out.println(kdTree.size());
        kdTree.insert(p2);
        System.out.println(kdTree.size());
        kdTree.insert(p1);
        System.out.println(kdTree.size());
        kdTree.insert(p4);
        System.out.println(kdTree.size());
        kdTree.insert(p5);
        System.out.println(kdTree.size());

        System.out.println(kdTree.contains(p1));
        System.out.println(kdTree.contains(p2));
        System.out.println(kdTree.contains(p3));
        System.out.println(kdTree.contains(p4));
        System.out.println(kdTree.contains(p5));
        System.out.println(kdTree.contains(p6));

        for (int i = 0; i < 1000000; i++) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            Point2D p = new Point2D(x, y);
            kdTree.insert(p);
        }

        System.out.println(kdTree.range(new RectHV(0, 0, 0.002, 0.002)));
        System.out.println(kdTree.nearest(new Point2D(0.5, 0.5)));
    }
}
