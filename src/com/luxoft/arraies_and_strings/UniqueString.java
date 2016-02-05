package com.luxoft.arraies_and_strings;


public class UniqueString {
    public static void main(String[] args) {
        String str = args[0];

        if (str.length() > 256)
            throw new RuntimeException("Too many characters to be unique");

        boolean[] uniqueArray = new boolean[256];
        char[] charArray = str.toCharArray();

        for(char c : charArray) {
            if (uniqueArray[c]) {
                throw new RuntimeException("Not unique string");
            } else {
                uniqueArray[c] = true;
            }
        }

        System.out.println("Unique string: " + str);
    }
}
