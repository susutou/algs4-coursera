package com.susutou.algorithm.algs4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * @author susen
 */
public class Solver {
    private static class SearchNode {
        private SearchNode prevSearchNode;
        private Board board;
        private int moves;

        public SearchNode(Board b) {
            this(b, null, 0);
        }

        public SearchNode(Board b, SearchNode prevSearchNode) {
            this(b, prevSearchNode, prevSearchNode.moves + 1);
        }

        private SearchNode(Board b, SearchNode prevSearchNode, int moves) {
            this.board = b;
            this.prevSearchNode = prevSearchNode;
            this.moves = moves;
        }
    }

    private int numMoves;
    private boolean isSolvable;
    private LinkedList<Board> solution;

    /**
     * find a solution to the initial board (using the A* algorithm)
     *
     * @param initial the initial board
     */
    public Solver(Board initial) {
        Comparator<SearchNode> comparator = new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                int p1 = o1.moves + o1.board.manhattan();
                int p2 = o2.moves + o2.board.manhattan();
                if (p1 == p2) {
                    return o1.board.manhattan() - o2.board.manhattan();
                } else {
                    return p1 - p2;
                }
            }
        };

        MinPQ<SearchNode> minPQ = new MinPQ<>(comparator);
        MinPQ<SearchNode> twinMinPQ = new MinPQ<>(comparator);

        SearchNode searchNode = new SearchNode(initial);
        SearchNode twinSearchNode = new SearchNode(initial.twin());

        while (!searchNode.board.isGoal() && !twinSearchNode.board.isGoal()) {
            for (Board b : searchNode.board.neighbors()) {
                if (searchNode.prevSearchNode == null || !b.equals(searchNode.prevSearchNode.board)) {
                    minPQ.insert(new SearchNode(b, searchNode));
                }
            }
            searchNode = minPQ.delMin();

            for (Board b : twinSearchNode.board.neighbors()) {
                if (twinSearchNode.prevSearchNode == null || !b.equals(twinSearchNode.prevSearchNode.board)) {
                    twinMinPQ.insert(new SearchNode(b, twinSearchNode));
                }
            }
            twinSearchNode = twinMinPQ.delMin();
        }

        if (searchNode.board.isGoal()) {
            isSolvable = true;
            numMoves = searchNode.moves;
            solution = new LinkedList<>();
            SearchNode n = searchNode;
            while (n != null) {
                solution.addFirst(n.board);
                n = n.prevSearchNode;
            }
        } else {
            isSolvable = false;
            numMoves = -1;
            solution = null;
        }

    }

    /**
     * @return is the initial board solvable?
     */
    public boolean isSolvable() {
        return isSolvable;
    }

    /**
     * @return min number of moves to solve initial board; -1 if no solution
     */
    public int moves() {
        return numMoves;
    }

    /**
     * @return sequence of boards in a shortest solution; null if no solution
     */
    public Iterable<Board> solution() {
        return solution;
    }

    /**
     * solve a slider puzzle (given below)
     */
    public static void main(String[] args) {

    }
}
