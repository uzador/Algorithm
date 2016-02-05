package com.luxoft.arraies_and_strings;

public class SetZerosRowAndColumnMatrix {
    public static void setZeros(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void fillMatrix(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = j;
            }
        }
    }

    public static void print(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = new int[5][6];
        fillMatrix(matrix);
        matrix[0][0] = 1;
        print(matrix);
        setZeros(matrix);
        print(matrix);
    }
}
