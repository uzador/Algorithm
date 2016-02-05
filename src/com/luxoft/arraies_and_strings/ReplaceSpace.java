package com.luxoft.arraies_and_strings;


public class ReplaceSpace {
    public static void main(String[] args) {
        String str = "Mr John Smith";
        int length = str.length();

        int count = 0;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == ' ') {
                count++;
            }
        }

        int newLength = length + (2 * count);
        char[] strArr = new char[newLength];

        int j = 0;
        for(char c : str.toCharArray()) {
            strArr[j++] = c;
        }

        strArr[newLength - 1] = '\0';
        for (int i = length - 1; i >= 0; i--) {
            if (strArr[i] == ' ') {
                strArr[newLength - 1] = '0';
                strArr[newLength - 2] = '2';
                strArr[newLength - 3] = '%';
                newLength = newLength - 3;
            } else {
                strArr[newLength - 1] = strArr[i];
                newLength--;
            }
        }

        System.out.println(strArr);
    }
}
