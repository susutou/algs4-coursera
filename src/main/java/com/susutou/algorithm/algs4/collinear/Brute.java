package com.susutou.algorithm.algs4.collinear;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdOut;

import java.util.Arrays;

/**
 * @author susen
 */
public class Brute {
    public static void main(String[] args) {
        StdDraw.setXscale(0.0D, 32768.0D);
        StdDraw.setYscale(0.0D, 32768.0D);

        String filename = args[0];
        In in = new In(filename);

        int n = in.readInt();
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];
                        if ((p.slopeTo(q) == p.slopeTo(r)) && (p.slopeTo(r) == p.slopeTo(s))) {
                            Point[] tuple = {p, q, r, s};
                            Arrays.sort(tuple);
                            tuple[0].drawTo(tuple[3]);
                            StdOut.printf(
                                    "%s -> %s -> %s -> %s\n",
                                    new Point[]{tuple[0], tuple[1], tuple[2], tuple[3]});
                        }
                    }
                }

            }

        }

        StdDraw.show(0);

        StdDraw.setPenRadius();
    }
}
