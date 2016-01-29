package com.susutou.algorithm.algs4.queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author susen
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] queue;
    private int size;

    public RandomizedQueue() {
        queue = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public static void main(String[] args) {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        assertNotNull(item);
        if (size == queue.length) {
            resize(queue.length * 2);
        }
        queue[size] = item;
        size += 1;
    }

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        @SuppressWarnings("unchecked") Item item = (Item) queue[randomIndex];
        queue[randomIndex] = queue[(size - 1)];
        queue[(size - 1)] = null;
        size -= 1;

        if ((size > 0) && (size == queue.length / 4)) {
            resize(queue.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        @SuppressWarnings("unchecked") Item item = (Item) queue[randomIndex];
        return item;
    }

    private void resize(int capacity) {
        assert (capacity >= size);
        if (capacity > size) {
            Object[] temp = new Object[capacity];
            System.arraycopy(queue, 0, temp, 0, size);
            queue = temp;
        }
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void assertNotNull(Item item) {
        if (item == null)
            throw new NullPointerException();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        private Object[] randomQueue;

        public RandomizedQueueIterator() {
            i = size;
            randomQueue = new Object[size];
            System.arraycopy(queue, 0, randomQueue, 0, size);
            StdRandom.shuffle(randomQueue);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            i -= 1;
            @SuppressWarnings("unchecked") Item item = (Item) randomQueue[i];
            randomQueue[i] = null;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
