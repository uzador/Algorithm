package com.luxoft.arraies_and_strings;

import java.util.Arrays;

public class StringPermutations {
    public static void main(String[] args) {
        String one = "oneone";
        String two = "oneone";

        if (one.length() != two.length())
            throw new RuntimeException("Not permutations: " + one + " AND " + two);

        char[] oneArray = one.toCharArray();
        char[] twoArray = two.toCharArray();
        Arrays.sort(oneArray);
        Arrays.sort(twoArray);


        if (Arrays.equals(oneArray, twoArray)) {
            System.out.println("Permutations: " + one + " AND " + two);
        } else {
            throw new RuntimeException("Not permutations: " + one + " AND " + two);
        }
    }
}
