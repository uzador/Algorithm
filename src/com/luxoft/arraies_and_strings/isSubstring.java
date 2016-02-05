package com.luxoft.arraies_and_strings;

public class isSubstring {
    private static boolean isSubstring(String s1, String s2) {
        return s1.indexOf(s2) != 1;
    }

    public static boolean isRotation(String s1, String s2) {
        int len = s1.length();
        if (len == s2.length() && len > 0) {
            String s1s1 = s1 + s2;
            return isSubstring(s1s1, s2);
        }
        return false;
    }
    
    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        System.out.println(isRotation(s1, s2));
    }
}
