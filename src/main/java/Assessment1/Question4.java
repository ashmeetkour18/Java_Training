package Assessment1;

import java.util.Scanner;

public class Question4 {
    public static void main(String[] args) {
        int row = 3;
        int[][] matrix = new int[row][row];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        System.out.println(checkSymmetricMatrix(matrix, row));
    }

    private static boolean checkSymmetricMatrix(int[][] matrix,int row) {
        for(int i=0;i<row;i++){
            for(int j=0;j<row;j++){
                //checking required at positions whose i and j are no equal
                // e.g [0][1] with [1][0],[0][2] with [2][0] similarly
                if(matrix[i][j]!=matrix[j][i]){
                    return false;
                }
            }
        }
        return true;
    }
}
