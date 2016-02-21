#!/usr/bin/env bash

rm -rf __submission__

mkdir __submission__
tail -n +2 "../src/main/java/com/susutou/algorithm/algs4/percolation/Percolation.java" > __submission__/Percolation.java
tail -n +2 "../src/main/java/com/susutou/algorithm/algs4/percolation/PercolationStats.java" > __submission__/PercolationStats.java
cd __submission__
zip percolation.zip *.java
rm -rf *.java

cd ..
tail -n +2 "../src/main/java/com/susutou/algorithm/algs4/queues/Deque.java" > __submission__/Deque.java
tail -n +2 "../src/main/java/com/susutou/algorithm/algs4/queues/RandomizedQueue.java" > __submission__/RandomizedQueue.java
tail -n +2 "../src/main/java/com/susutou/algorithm/algs4/queues/Subset.java" > __submission__/Subset.java
cd __submission__
zip queues.zip *.java
rm -rf *.java

cd ..
tail -n +2 "../src/main/java/com/susutou/algorithm/algs4/collinear/Point.java" > __submission__/Point.java
tail -n +2 "../src/main/java/com/susutou/algorithm/algs4/collinear/BruteCollinearPoints.java" > __submission__/BruteCollinearPoints.java
tail -n +2 "../src/main/java/com/susutou/algorithm/algs4/collinear/FastCollinearPoints.java" > __submission__/FastCollinearPoints.java
cd __submission__
zip collinear.zip *.java
rm -rf *.java

cd ..
tail -n +2 "../src/main/java/com/susutou/algorithm/algs4/puzzle/Board.java" > __submission__/Board.java
tail -n +2 "../src/main/java/com/susutou/algorithm/algs4/puzzle/Solver.java" > __submission__/Solver.java
cd __submission__
zip 8puzzle.zip *.java
rm -rf *.java
