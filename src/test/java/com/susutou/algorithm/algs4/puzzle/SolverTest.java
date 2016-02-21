package com.susutou.algorithm.algs4.puzzle;

import org.junit.Test;

/**
 * @author susen
 */
public class SolverTest {
    @Test
    public void testAll() {
        Board b = new Board(new int[][] {{8, 6, 7}, {2, 5, 4}, {3, 0, 1}});
        Solver solver = new Solver(b);

        System.out.println(solver.isSolvable());
        System.out.println(solver.moves());
        System.out.println(solver.solution());
    }
}