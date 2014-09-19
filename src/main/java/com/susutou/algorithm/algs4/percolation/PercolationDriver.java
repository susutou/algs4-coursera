package com.susutou.algorithm.algs4.percolation;

import edu.princeton.cs.introcs.StdOut;

/**
 * @author susen
 */
public class PercolationDriver {
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(20, 10);
        StdOut.println(stats.mean());

        stats = new PercolationStats(50, 20);
        StdOut.println(stats.mean());

        stats = new PercolationStats(100, 50);
        StdOut.println(stats.mean());

        stats = new PercolationStats(64, 150);
        StdOut.println(stats.mean());
    }
}
