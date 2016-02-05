package com.luxoft.linked_lists;

import java.util.HashMap;
import java.util.Map;

class IntWrapper {
    int value = 0;
}

class Node {
    Node next = null;
    int data;

    public Node(int d) {
        data = d;
    }

    static Node deleteNode(Node root, int d) {
        if (root == null) {
            return null;
        } else {
            Node n = root;
            if (n.data == d) {
                return root.next;
            }

            while (n.next != null) {
                if (n.next.data == d) {
                    n.next = n.next.next;
                    return root;
                }
                n = n.next;
            }
            return root;
        }
    }

    static void print(Node root) {
        if (root == null) {
            System.out.printf("LinkedList is empty");
        } else {
            Node n = root;
            System.out.print(n.data + " ");
            while (n.next != null) {
                n = n.next;
                System.out.print(n.data + " ");
            }
        }
        System.out.println();
    }

    static void removeDups(Node n) {
        if (n == null) {
            return;
        }

        Node current = n;
        while (current != null) {
            Node runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    static Node kThNode(Node root, int k) {
        if (root == null)
            return null;

        Node cur = root;
        int size = 1;
        while (cur.next != null) {
            cur = cur.next;
            size++;
        }

        Node n = root;
        for (int i = 0; i < size - k; i++) {
            n = n.next;
        }

        return n;
    }

    static int nthToLst(Node head, int k) {
        if (head == null) {
            return 0;
        }
        int i = nthToLst(head.next, k) + 1;
        if (i == k) {
            System.out.println(head.data);
        }
        return i;
    }

    static Node nthToList(Node head, int k, IntWrapper i) {
        if (head == null) {
            return null;
        }
        Node node = nthToList(head.next, k, i);
        i.value = i.value + 1;
        if (i.value == k) {
            return head;
        }
        return node;
    }

    @Override
    public String toString() {
        return "" + data;
    }

    void appendToTail(int d) {
        Node end = new Node(d);
        Node n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    static Node partition(Node root, int x) {
        Node beforeStart = null;
        Node beforeEnd = null;
        Node afterStart = null;
        Node afterEnd = null;

        while (root != null) {
            Node next = root.next;
            root.next = null;
            if (root.data < x) {
                if (beforeStart == null) {
                    beforeStart = root;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.next = root;
                    beforeEnd = root;
                }
            } else {
                if (afterStart == null) {
                    afterStart = root;
                    afterEnd = afterStart;
                } else {
                    afterEnd.next = root;
                    afterEnd = root;
                }
            }
            root = next;
        }
        if (beforeStart == null) {
            return afterStart;
        }
        beforeEnd.next = afterStart;
        return beforeStart;
    }
}

public class MyLinkedList {
    public static void main(String[] args) {
        Node n = new Node(10);
        for (int i = 2; i < 10; i++) {
            n.appendToTail(i * 10);
        }

        n = Node.deleteNode(n, 100);
        Node.print(n);

        n = Node.deleteNode(n, 10);
        Node.print(n);

        n = Node.deleteNode(n, 70);
        Node.print(n);

        n.appendToTail(40);
        n.appendToTail(40);
        n.appendToTail(40);
        Node.print(n);

        Node.removeDups(n);
        Node.print(n);

        Node k = Node.kThNode(n, 5);
        System.out.println(k);

        Node.nthToLst(n, 5);

        System.out.println(Node.nthToList(n, 5, new IntWrapper()));

        n.appendToTail(1);
        n.appendToTail(100);
        n.appendToTail(2);
        n.appendToTail(200);
        Node.partition(n, 60).print(n);
    }
}
