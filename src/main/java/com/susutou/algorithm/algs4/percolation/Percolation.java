package com.susutou.algorithm.algs4.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author susen
 */
public class Percolation {
    private int size;
    private boolean[][] grid;
    private WeightedQuickUnionUF finderTop;
    private WeightedQuickUnionUF finder;
    private int virtualTop;
    private int virtualBottom;

    public Percolation(int N) {
        if (N < 1) {
            throw new IllegalArgumentException("N must be an integer greater than or equal to 1.");
        }

        this.size = N;
        this.grid = new boolean[N][N];
        this.finderTop = new WeightedQuickUnionUF(N * N + 2);
        this.finder = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.grid[i][j] = false;
            }
        }
        this.virtualTop = 0;
        this.virtualBottom = (N * N + 1);
    }

    public void open(int i, int j) {
        set(i, j);
        if (isOpenSafe(i - 1, j)) {
            union(i, j, i - 1, j);
        }
        if (isOpenSafe(i + 1, j)) {
            union(i, j, i + 1, j);
        }
        if (isOpenSafe(i, j - 1)) {
            union(i, j, i, j - 1);
        }
        if (isOpenSafe(i, j + 1)) {
            union(i, j, i, j + 1);
        }
        if (i == 1) {
            union(this.finderTop, this.virtualTop, i, j);
            union(this.finder, this.virtualTop, i, j);
        }
        if (i == this.size)
            union(this.finder, this.virtualBottom, i, j);
    }

    public boolean isOpen(int i, int j) {
        return get(i, j);
    }

    public boolean isFull(int i, int j) {
        assertValidIndices(i, j);
        return (isConnected(this.finderTop, this.virtualTop, i, j)) && (isConnected(this.finder, this.virtualTop, i, j));
    }

    public boolean percolates() {
        return this.finder.connected(this.virtualTop, this.virtualBottom);
    }

    private boolean isOpenSafe(int i, int j) {
        boolean isOpen;
        try {
            isOpen = isOpen(i, j);
        } catch (IndexOutOfBoundsException e) {
            isOpen = false;
        }

        return isOpen;
    }

    private void assertValidIndices(int i, int j) {
        if ((i < 1) || (i > this.size) || (j < 1) || (j > this.size))
            throw new IndexOutOfBoundsException("Indices i and j must stay in the range of [1, N].");
    }

    private void set(int i, int j) {
        assertValidIndices(i, j);
        this.grid[(i - 1)][(j - 1)] = true;
    }

    private boolean get(int i, int j) {
        assertValidIndices(i, j);
        return this.grid[(i - 1)][(j - 1)];
    }

    private void union(int iP, int jP, int iQ, int jQ) {
        int p = convert(iP, jP);
        int q = convert(iQ, jQ);
        this.finder.union(p, q);
        this.finderTop.union(p, q);
    }

    private void union(WeightedQuickUnionUF uf, int p, int iQ, int jQ) {
        int q = convert(iQ, jQ);
        uf.union(p, q);
    }

    private boolean isConnected(WeightedQuickUnionUF uf, int p, int iQ, int jQ) {
        int q = convert(iQ, jQ);
        return uf.connected(p, q);
    }

    private int convert(int i, int j) {
        assertValidIndices(i, j);
        return (i - 1) * this.size + (j - 1) + 1;
    }
}
