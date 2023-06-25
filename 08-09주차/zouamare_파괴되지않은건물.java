package coding_test.AlgoY2023.M04.D30;

public class 파괴되지않은건물 {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}}, new int[][]{{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}}));
    }

    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] sum = new int[board.length + 1][board[0].length + 1];

        /*
        * 9 9 +
        * 9 9 0
        * + 0 -
        * */

        for(int i = 0; i < skill.length; i++){
            if(skill[i][0] == 1){   // 적의 공격 (내구도 -)
                sum[skill[i][1]][skill[i][2]] -= skill[i][5];
                sum[skill[i][1]][skill[i][4] + 1] += skill[i][5];
                sum[skill[i][3] + 1][skill[i][2]] += skill[i][5];
                sum[skill[i][3] + 1][skill[i][4] + 1] -= skill[i][5];
            }
            else{   // 아군의 회복 (내구도 +)
                sum[skill[i][1]][skill[i][2]] += skill[i][5];
                sum[skill[i][1]][skill[i][4] + 1] -= skill[i][5];
                sum[skill[i][3] + 1][skill[i][2]] -= skill[i][5];
                sum[skill[i][3] + 1][skill[i][4] + 1] += skill[i][5];
            }
        }

        for(int i = 1; i < board[0].length; i++){
            sum[0][i] += sum[0][i-1];
        }

        for(int i = 1; i < board.length; i++){
            sum[i][0] += sum[i-1][0];
        }

        for(int i = 1; i < board.length; i++){
            for(int j = 1; j < board[i].length; j++){
                sum[i][j] += sum[i-1][j] + sum[i][j-1];
                sum[i][j] -= sum[i-1][j-1];
            }
        }

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] + sum[i][j] > 0){
                    answer++;
                }
            }
        }
        return answer;
    }
}
