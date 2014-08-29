package com.susutou.algorithm.algs4.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author susen
 */
public class Deque<Item> implements Iterable<Item> {
    private DequeNode first;
    private DequeNode last;
    private int size;

    public Deque() {
        first = null;
        last = null;
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

    public void addFirst(Item item) {
        assertNotNull(item);
        if (size == 0) {
            addInitial(item);
        } else {
            DequeNode node = new DequeNode(item, null, first);
            first.prev = node;
            first = node;
            size += 1;
        }
    }

    public void addLast(Item item) {
        assertNotNull(item);
        if (size == 0) {
            addInitial(item);
        } else {
            DequeNode node = new DequeNode(item, last, null);
            last.next = node;
            last = node;
            size += 1;
        }
    }

    private void addInitial(Item item) {
        if (size != 0) {
            throw new UnsupportedOperationException();
        }
        DequeNode node = new DequeNode(item, null, null);
        first = node;
        last = node;
        size += 1;
    }

    public Item removeFirst() {
        if (size == 0)
            throw new NoSuchElementException();
        if (size == 1) {
            return removeTheLastOne();
        }
        Item item = first.item;
        DequeNode next = first.next;
        next.prev = null;
        first = next;
        size -= 1;
        return item;
    }

    public Item removeLast() {
        if (size == 0)
            throw new NoSuchElementException();
        if (size == 1) {
            return removeTheLastOne();
        }
        Item item = last.item;
        DequeNode prev = last.prev;
        prev.next = null;
        last = prev;
        size -= 1;
        return item;
    }

    private Item removeTheLastOne() {
        if (size != 1) {
            throw new UnsupportedOperationException();
        }
        Item item = first.item;
        first = null;
        last = null;
        size -= 1;
        return item;
    }

    private void assertNotNull(Item item) {
        if (item == null)
            throw new NullPointerException();
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeNode {
        private DequeNode prev;
        private DequeNode next;
        private Item item;

        private DequeNode(Item item, DequeNode prev, DequeNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private DequeNode current = first;

        private DequeIterator() {
        }

        public boolean hasNext() {
            return current != null;
        }


        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
