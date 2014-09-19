package com.susutou.algorithm.algs4.puzzle;

import edu.princeton.cs.introcs.StdOut;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author susen
 */
public class Board {
    private int[][] blocks;  // blocks
    private int n;  // dimension
    private int emptyRow;
    private int emptyCol;  // position of empty spot on board

    public Board(int[][] blocks) {
        assert blocks.length > 0 && blocks.length == blocks[0].length;    /* mark */
        this.n = blocks.length;
        this.blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(blocks[i], 0, this.blocks[i], 0, n);
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0) {
                    this.emptyRow = i;
                    this.emptyCol = j;
                }
            }
        }
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        int distance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int block = blocks[i][j];
                if (block != 0) {
                    // convert row and col to block number in goal
                    int goalBlock = i * n + j + 1;
                    if (block != goalBlock) {
                        distance++;
                    }
                }
            }
        }
        return distance;
    }

    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int block = blocks[i][j];
                // convert block number to row and col in goal
                if (block != 0) {
                    int col = (block - 1) % n;
                    int row = (block - 1) / n;
                    distance += Math.abs(i - row) + Math.abs(j - col);
                }
            }
        }
        return distance;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        return null;
    }

    @Override
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        } else if (y instanceof Board) {
            if (y == this) {
                return true;
            } else if (((Board) y).dimension() == n) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (((Board) y).blocks[i][j] != blocks[i][j]) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    public Iterable<Board> neighbors() {
        LinkedList<Board> boards = new LinkedList<Board>();
        // move from up
        if (emptyRow > 0) {
            boards.add(swap(emptyRow - 1, emptyCol));
        }
        // move from down
        if (emptyRow < n - 1) {
            boards.add(swap(emptyRow + 1, emptyCol));
        }
        // move from left
        if (emptyCol > 0) {
            boards.add(swap(emptyRow, emptyCol - 1));
        }
        // move from down
        if (emptyCol < n - 1) {
            boards.add(swap(emptyRow, emptyCol + 1));
        }
        return boards;
    }

    // swap block[row, col] with empty block and return the new Board
    private Board swap(int row, int col) {
        // ensure that all swaps are legal
        assert row < n && col < n && Math.abs(row - emptyRow) == 1 || Math.abs(col - emptyCol) == 1;
        // swap
        blocks[emptyRow][emptyCol] = blocks[row][col];
        blocks[row][col] = 0;
        // generate neighbor
        Board neighbor = new Board(blocks);
        // swap back
        blocks[row][col] = blocks[emptyRow][emptyCol];
        blocks[emptyRow][emptyCol] = 0;

        return neighbor;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(n).append('\n');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                builder.append(String.format("%2d ", blocks[i][j]));
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        int[][] blocks = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
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
    }
}
