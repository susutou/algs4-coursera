package com.susutou.algorithm.algs4.collinear;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdOut;

import java.util.Arrays;

/**
 * @author susen
 */
public class Fast {
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

        Arrays.sort(points);

        for (int i = 0; i < n - 4; i++) {
            Point origin = points[i];
            Arrays.sort(points, i + 1, n, origin.SLOPE_ORDER);
            int j = i + 1;
            double slope = origin.slopeTo(points[j]);
            int continuityCounter = 1;
            while (true) {
                j++;
                if ((j < n) && (origin.slopeTo(points[j]) == slope)) {
                    continuityCounter++;
                } else {
                    if (continuityCounter >= 3) {
                        Point[] segment = new Point[continuityCounter + 1];
                        segment[0] = origin;
                        System.arraycopy(
                                points, j - continuityCounter,
                                segment, j - continuityCounter - (j - continuityCounter) + 1,
                                j - (j - continuityCounter));

                        Arrays.sort(segment);

                        segment[0].drawTo(segment[(segment.length - 1)]);
                        for (int k = 0; k < segment.length; k++) {
                            if (k == 0)
                                StdOut.print(segment[k]);
                            else {
                                StdOut.print(" -> " + segment[k]);
                            }
                        }
                        StdOut.println();
                    }
                    if (j + 1 >= n) break;
                    slope = origin.slopeTo(points[j]);
                    continuityCounter = 1;
                }

            }

        }

        StdDraw.show(0);

        StdDraw.setPenRadius();
    }
}
