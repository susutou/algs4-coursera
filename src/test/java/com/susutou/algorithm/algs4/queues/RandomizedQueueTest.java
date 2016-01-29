package com.susutou.algorithm.algs4.queues;

import com.google.common.collect.Iterables;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

public class RandomizedQueueTest {
    private static final int NUM_ENQUEUE = 100;

    @Test
    public void testIsEmpty() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        Assert.assertEquals(queue.isEmpty(), true);
        queue.enqueue(1);
        Assert.assertEquals(queue.isEmpty(), false);
        queue.dequeue();
        Assert.assertEquals(queue.isEmpty(), true);
    }

    @Test
    public void testSize() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < NUM_ENQUEUE; i++) {
            queue.enqueue(i);
        }
        Assert.assertEquals(queue.size(), NUM_ENQUEUE);
        int numDequeue = StdRandom.uniform(NUM_ENQUEUE) + 1;
        for (int i = 0; i < numDequeue; i++) {
            queue.dequeue();
        }
        Assert.assertEquals(queue.size(), 100 - numDequeue);
    }

    @Test
    public void testEnqueueDequeue() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < NUM_ENQUEUE; i++) {
            queue.enqueue(i);
        }
        System.out.println(Iterables.toString(queue));
    }

    @Test
    public void testSample() throws Exception {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < NUM_ENQUEUE; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue.sample());
    }

    @Test
    public void testIterator() throws Exception {

    }
}