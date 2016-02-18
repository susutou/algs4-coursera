package com.susutou.algorithm.algs4.puzzle;

import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    private int[][] blocks;

    @Before
    public void setUp() throws Exception {
        blocks = new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    }

    @Test
    public void testAll() throws Exception {
        Board b = new Board(blocks);
        Board bClone = new Board(blocks);

        StdOut.println(b);
        StdOut.println(b.dimension());
        StdOut.println(b.hamming());
        StdOut.println(b.manhattan());
        StdOut.println(b.equals(new Object()));
        StdOut.println(b.equals(b));
        StdOut.println(b.equals(bClone));
        StdOut.println(b.equals(bClone) == bClone.equals(b));

        StdOut.println("Neighbors - ");
        for (Board neighbor : b.neighbors()) {
            StdOut.println(neighbor);
        }

        StdOut.println("Original Board - ");
        StdOut.println(b);

        StdOut.println("Twin Board - ");
        StdOut.println(b.twin());
    }
}