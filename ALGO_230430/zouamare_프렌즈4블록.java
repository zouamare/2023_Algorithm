package coding_test.AlgoY2023.M05.D21;

import java.util.Arrays;

public class 프렌즈4블록 {
    public static void main(String[] args) {
        System.out.println(solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"}));
    }
    public static int solution(int m, int n, String[] board) {
        char[][] matrix = new char[m][n];
        int count = 0;
        for(int i=0; i<m; i++){
            matrix[i] = board[i].toCharArray();
        }

        boolean[][] check = new boolean[m][n];
        boolean flag = true;

        while(flag){
            flag = false;
            for(int i = 0; i < m; i++){
                Arrays.fill(check[i], false);
            }

            for(int i = 0; i < m - 1; i++){
                for(int j = 0; j < n - 1; j++){
                    if(matrix[i][j] != ' '
                            && matrix[i][j] == matrix[i+1][j]
                            && matrix[i][j] == matrix[i][j+1]
                            && matrix[i][j] == matrix[i+1][j+1]){
                        check[i][j] = check[i+1][j] = check[i][j+1] = check[i+1][j+1] = true;
                        flag = true;
                    }
                }
            }

            if(flag){
                // 2x2 체크된 블록 지우기
                for(int i = 0; i < m; i++){
                    for(int j = 0; j < n; j++){
                        if(check[i][j]){
                            matrix[i][j] = ' ';
                            count++;
                        }
                    }
                }
                // 블록 아래로 당기기
                for(int i = 0; i < n; i++){
                    int blank = -1;
                    int pos = m - 1;
                    while(pos >= 0){
                        if(matrix[pos][i] == ' '){
                            if(blank == -1)
                                blank = pos;
                        }
                        else{
                            if(blank != -1){
                                matrix[blank][i] = matrix[pos][i];
                                matrix[pos][i] = ' ';
                                while(matrix[blank][i] != ' ' && blank > 0){
                                    blank--;
                                }

                            }
                        }
                        pos--;
                    }
                }
            }
        }
        return count;
    }
}
