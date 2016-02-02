package com.susutou.algorithm.algs4.queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author susen
 */
public class Subset {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException();
        }

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int count = 0;

        while (k > 0 && !StdIn.isEmpty()) {
            String token = StdIn.readString();
            count++;

            // reservoir sampling
            if (queue.size() < k) {
                queue.enqueue(token);
            } else {
                int r = StdRandom.uniform(count);
                if (r < k) {
                    queue.dequeue();
                    queue.enqueue(token);
                }
            }
        }

        for (String token : queue) {
            System.out.println(token);
        }
    }
}
