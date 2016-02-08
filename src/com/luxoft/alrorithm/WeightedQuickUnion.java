package com.luxoft.alrorithm;

public class WeightedQuickUnion {
    private int[] id;
    private int[] sz;

    public WeightedQuickUnion(int N) {
        id = new int[N];
        sz = new int[N];
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
        WeightedQuickUnion wqu = new WeightedQuickUnion(10);
        wqu.union(1,2);

        System.out.println(wqu.connected(1,3));
    }
}
