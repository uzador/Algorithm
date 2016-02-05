package com.luxoft.arraies_and_strings;

public class CountRepeatChars {
    public static String compressedString(String message) {
        StringBuilder mystr = new StringBuilder();

        char last = message.charAt(0);
        int count = 1;
        for (int i = 1; i < message.length(); i++) {
            if (message.charAt(i) == last) {
                count++;
            } else {
                mystr.append(last).append(count);
                last = message.charAt(i);
                count = 1;
            }
        }

        return mystr.append(last).append(count).toString();
    }

    public static void main(String[] args) {
//        String str = "aabccccaaaa";
        String str = "abcd";
        System.out.println("String: " + str);
        System.out.println("compressed string: " + compressedString(str));
    }
}
