package com.luxoft.alrorithm;

public class QuickFind {
    private int[] id;

    public QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int i, int j) {
        return id[i] == id[j];
    }

    public void union(int i, int j) {
        int first = id[i];
        int second = id[j];
        for (int k = 0; k < id.length; k++) {
            if (id[k] == first) id[k] = second;
        }
    }

    public static void main(String[] args) {
        QuickFind qf = new QuickFind(10);
        qf.union(1,2);

        if (null != new Integer(10)) System.out.println("true");
        else System.out.println("false");
    }
}
