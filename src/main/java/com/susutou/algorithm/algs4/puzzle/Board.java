package com.susutou.algorithm.algs4.puzzle;

import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;

/**
 * @author susen
 */
public class Board {
    private short[][] blocks;  // blocks
    private int n;  // dimension
    private int emptyRow;
    private int emptyCol;  // position of empty spot on board
    private int hammingDistance = -1;
    private int manhattanDistance = -1;

    /**
     * construct a board from an N-by-N array of blocks (where blocks[i][j] = block in row i, column j)
     *
     * @param blocks N-by-N array of blocks
     */
    public Board(int[][] blocks) {
        assert blocks.length > 0 && blocks.length == blocks[0].length;    /* mark */
        this.n = blocks.length;
        this.blocks = new short[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.blocks[i][j] = (short) blocks[i][j];
                if (blocks[i][j] == 0) {
                    this.emptyRow = i;
                    this.emptyCol = j;
                }
            }
        }
    }

    /**
     * construct a board from an N-by-N array of blocks plus swapping (rowA, colA) <-> (rowB, colB)
     */
    private Board(short[][] blocks, int rowA, int colA, int rowB, int colB, int initialManhattanDistance) {
        assert blocks.length > 0 && blocks.length == blocks[0].length;
        this.n = blocks.length;
        this.blocks = new short[n][n];
        validateCoordinates(n, rowA, colA, rowB, colB);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.blocks[i][j] = (short) blocks[i][j];
                if (blocks[i][j] == 0) {
                    this.emptyRow = i;
                    this.emptyCol = j;
                }
            }
        }

        short temp = this.blocks[rowA][colA];
        this.blocks[rowA][colA] = this.blocks[rowB][colB];
        this.blocks[rowB][colB] = temp;

        if (this.blocks[rowA][colA] == 0) {
            emptyRow = rowA;
            emptyCol = colA;
        } else if (this.blocks[rowB][colB] == 0) {
            emptyRow = rowB;
            emptyCol = colB;
        }


        this.manhattanDistance = initialManhattanDistance;
    }

    /**
     * @return board dimension N
     */
    public int dimension() {
        return n;
    }

    /**
     * @return number of blocks out of place
     */
    public int hamming() {
        if (hammingDistance == -1) {
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
            hammingDistance = distance;
        }

        return hammingDistance;
    }

    /**
     * @return sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        if (manhattanDistance == -1) {
            int distance = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int block = blocks[i][j];
                    // convert block number to row and col in goal
                    distance += singleManhattan(block, i, j);
                }
            }
            manhattanDistance = distance;
        }

        return manhattanDistance;
    }

    private int singleManhattan(int block, int i, int j) {
        if (block != 0) {
            int col = (block - 1) % n;
            int row = (block - 1) / n;
            return Math.abs(i - row) + Math.abs(j - col);
        } else {
            return 0;
        }
    }

    /**
     * @return is this board the goal board?
     */
    public boolean isGoal() {
        return hamming() == 0;
    }

    /**
     * @return a board that is obtained by exchanging any pair of blocks
     */
    public Board twin() {
        int rowA = 0;
        int colA = 0;
        int rowB = 0;
        int colB = 0;

        while ((rowA == rowB && colA == colB) || blocks[rowA][colA] == 0 || blocks[rowB][colB] == 0) {
            rowA = StdRandom.uniform(n);
            colA = StdRandom.uniform(n);
            rowB = StdRandom.uniform(n);
            colB = StdRandom.uniform(n);
        }

        return new Board(blocks, rowA, colA, rowB, colB, -1);
    }

    /**
     * @param other another Board other
     * @return does this board equal other?
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other.getClass() != this.getClass()) {
            return false;
        } else {
            Board ob = (Board) other;
            if (ob.dimension() != n) {
                return false;
            } else {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (ob.blocks[i][j] != blocks[i][j]) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
    }

    /**
     * @return all neighboring boards
     */
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

    /**
     * @return string representation of this board (in the output format specified below)
     */
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

    // swap block[row, col] with empty block and return the new Board
    private Board swap(int row, int col) {
        int singleManhattanAfterSwap = singleManhattan(blocks[row][col], emptyRow, emptyCol);
        int singleManhattanBeforeSwap = singleManhattan(blocks[row][col], row, col);
        int diff = singleManhattanAfterSwap - singleManhattanBeforeSwap;
        return new Board(blocks, emptyRow, emptyCol, row, col, manhattan() + diff);
    }

    private void validateCoordinates(int upperBound, int ...coordinates) {
        for (int c : coordinates) {
            assert c >= 0 && c < upperBound;
        }
    }
}
