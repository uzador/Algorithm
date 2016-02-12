import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by yzadorozhnyy on 10.02.2016.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int head;
    private Item[] rq = (Item[]) new Object[1];
    private int size;

    public RandomizedQueue() {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new NullPointerException("Couldn't add null value to deque");
        }
    }

    private void validateQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue is empty");
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int s = size;
        for (int i = 0; i < size; i++) {
            copy[i] = rq[i];
        }
        rq = copy;
    }

    public void enqueue(Item item) {
        validateItem(item);

        if (size == rq.length) {
            resize(2 * rq.length);
        }
        rq[size++] = item;
    }

    public Item sample() {
        validateQueue();

        int pos = StdRandom.uniform(size);
        Item result = rq[pos];

        return result;
    }

    public Item dequeue() {
        validateQueue();

        int randPos = StdRandom.uniform(size);
        Item result = rq[randPos];

        if (randPos != size - 1) {
            rq[randPos] = rq[size - 1];
        }
        rq[size - 1] = null;

        if (size == rq.length / 4) {
            resize(rq.length / 2);
        }
        --size;

        return result;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>();
    }

    private void print() {
        Iterator<Item> iterator = iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {
        private int count = 0;
        private int[] indexes;

        public RandomizedQueueIterator() {
            indexes = new int[size];
            for (int i = 0; i < size; i++) {
                indexes[i] = i;
            }
            StdRandom.shuffle(indexes);
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no elements in the randomizedqueue");
            }
            Item result = (Item) rq[indexes[count++]];

            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove method is not supported yet");
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        rq.enqueue(434);
        rq.enqueue(362);
        rq.isEmpty();
        rq.dequeue();
        rq.enqueue(969);
        rq.enqueue(399);
        rq.dequeue();
    }
}
