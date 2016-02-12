import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by yzadorozhnyy on 10.02.2016.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head, tail;
    private int size;

    public Deque() {
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new NullPointerException("Couldn't add null value to deque");
        }
    }

    private void validateDeque() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
    }

    public void addFirst(Item item) {
        validateItem(item);
        Node<Item> newHead = new Deque<Item>.Node<Item>(item);

        if (size == 0) {
            tail = newHead;
        } else {
            Node<Item> oldHead = head;
            oldHead.left = newHead;
            newHead.right = oldHead;
        }
        head = newHead;
        size++;
    }

    public void addLast(Item item) {
        validateItem(item);
        Node<Item> last = new Deque<Item>.Node<Item>(item);

        if (size == 0) {
            head = last;
            tail = last;
        } else {
            Node<Item> oldLast = tail;
            oldLast.right = last;
            last.left = oldLast;
        }
        tail = last;
        size++;
    }

    public Item removeFirst() {
        validateDeque();

        Item result = head.value;
        head.value = null;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.right;
        }
        size--;

        return result;
    }

    public Item removeLast() {
        validateDeque();

        Item result = tail.value;
        tail.value = null;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node<Item> prevTail = tail.left;
            prevTail.right = null;
            tail = prevTail;
        }
        size--;

        return result;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>();
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> current = (Node<Item>) head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There are no elements in the deque");
            }

            Item result = current.value;
            current = current.right;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove method is not supported yet");
        }
    }

    private class Node<Item> {
        private Node<Item> left, right;
        private Item value;

        public Node(Item value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(10);
        deque.addFirst(20);
        deque.addFirst(30);
        deque.addLast(100);
        deque.addLast(200);
        deque.addLast(300);
        deque.removeFirst();
        deque.removeLast();
        Iterator<Integer> iter = deque.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
