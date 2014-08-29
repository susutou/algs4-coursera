package com.susutou.algorithm.algs4.queues;

import com.google.common.collect.Iterables;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class DequeTest {
    private static final int NUM_ADD = 100;
    private static final int NUM_REMOVE = 50;
    private static Runtime runtime;

    @BeforeClass
    public static void setUp() {
        runtime = Runtime.getRuntime();
    }

    @Test
    public void testDeque() {
        Deque<Integer> deque = new Deque<>();
        LinkedList<Integer> goldenDeque = new LinkedList<>();

        Assert.assertEquals(goldenDeque.isEmpty(), deque.isEmpty());

        for (int i = 0; i < NUM_ADD; i++) {
            boolean b = StdRandom.bernoulli();
            int item = StdRandom.uniform(NUM_ADD);
            if (b) {
                deque.addFirst(item);
                goldenDeque.addFirst(item);
                Assert.assertEquals(Iterables.toString(goldenDeque), Iterables.toString(deque));
            } else {
                deque.addLast(item);
                goldenDeque.addLast(item);
                Assert.assertEquals(Iterables.toString(goldenDeque), Iterables.toString(deque));
            }
        }

        System.out.println(runtime.totalMemory());

        Assert.assertEquals(Iterables.toString(goldenDeque), Iterables.toString(deque));

        for (int i = 0; i < NUM_REMOVE; i++) {
            boolean b = StdRandom.bernoulli();
            if (b) {
                int real = deque.removeFirst();
                int golden = goldenDeque.removeFirst();
                Assert.assertEquals(golden, real);
                Assert.assertEquals(Iterables.toString(goldenDeque), Iterables.toString(deque));
            } else {
                int real = deque.removeLast();
                int golden = goldenDeque.removeLast();
                Assert.assertEquals(golden, real);
                Assert.assertEquals(Iterables.toString(goldenDeque), Iterables.toString(deque));
            }
        }

        System.out.println(runtime.totalMemory());

        Assert.assertEquals(goldenDeque.size(), deque.size());
        Assert.assertEquals(goldenDeque.isEmpty(), deque.isEmpty());
        Assert.assertEquals(Iterables.toString(goldenDeque), Iterables.toString(deque));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemoveThrowsException() {
        Deque deque = new Deque();
        deque.iterator().remove();
    }

    @Test(expected = NullPointerException.class)
    public void testAddingNullItemToDeque() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromEmptyDeque() {
        Deque deque = new Deque();
        deque.removeFirst();
    }

    @Test
    public void testStateChange() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(10);
        deque.removeLast();
        deque.addLast(9);
        Assert.assertEquals("[9]", Iterables.toString(deque));
    }
}