package com.susutou.algorithm.algs4.queues;

import edu.princeton.cs.algs4.StdIn;

/**
 * @author susen
 */
public class Subset {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException();
        }

        int k = Integer.parseInt(args[0]);
        int n = 0;
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();
            queue.enqueue(token);
            n++;
        }

        for (int i = 0; i < n - k; i++) {
            queue.dequeue();
        }

        for (String token : queue)
            System.out.println(token);
    }
}
