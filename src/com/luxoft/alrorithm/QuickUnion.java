package com.luxoft.alrorithm;

public class QuickUnion {
    private int[] id;

    public QuickUnion(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    public boolean connected(int i, int j) {
        return root(i) == root(j);
    }

    public void union(int i, int j) {
        int first = root(i);
        int second = root(j);
        id[first] = second;
    }

    public static void main(String[] args) {
        QuickUnion qu = new QuickUnion(10);
        qu.union(1,2);

        System.out.println(qu.connected(1,3));
    }
}
