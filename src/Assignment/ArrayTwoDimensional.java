package Assignment;

import java.util.Scanner;

public class ArrayTwoDimensional {
    public static void showArray(int row, int column) {
        char[][] arr = new char[row][column];

        char a = 'A';
        System.out.println("Grades of 3 students");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                arr[i][j] = a;
            }
            a++;
        }
        arr[1][4] = 'C';
        arr[2][0] = 'A';
        String s = "        ";
        for (int i = -1; i < row; i++) {
            if (i >= 0)
                System.out.print("Student" + (i + 1) + " ");
            else {
                System.out.print("         ");
            }
            for (int j = -1; j < column; j++) {
                if (i == -1 && j < column - 1) {
                    System.out.print("courses" + (j + 2) + " ");
                }
                if (j >= 0 && i >= 0) {
                    System.out.print(" " + arr[i][j] + "       ");
                }

            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        int row = 3;
        int column = 5;
        showArray(row, column);
    }

}
