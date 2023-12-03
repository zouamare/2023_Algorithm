package coding_test.AlgoY2023.M12.D02;

public class 양궁대회 {
    static int maxScore;
    static int[] answer = new int[11];
    static int[] infoApeach;

    // 맨 처음에는 boolean[] visited로만 계산하다가 결국 int[]로 변경하여 구함

    public static void main(String[] args) {
        System.out.println(solution(9, new int[]{0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1}));
    }

    public static int[] solution(int n, int[] info) {
        infoApeach = info;
        maxScore = Integer.MIN_VALUE;
        int apeachScore = 0;
        for(int i = 0; i < 11; i++){
            if(info[i] != 0){
                apeachScore += 10 - i;
            }
        }
        isLionWin(0, new int[11], n, 0, apeachScore);
        if(maxScore == Integer.MIN_VALUE)	return new int[] {-1};
        return answer;
    }

    private static void isLionWin(int depth, int[] visited, int leftArrow, int score, int apeachScore) {
        if(depth > 10 || leftArrow == 0) {
            if(score > apeachScore) {
                if(leftArrow > 0){
                    visited[10] = leftArrow;
                }
                if(maxScore < score - apeachScore){
                    answer = visited;
                    maxScore = score - apeachScore;
                    return;
                }
                else if(maxScore == score - apeachScore){
                    for(int i = 10; i >= 0; i--){
                        if(visited[i] > answer[i]){
                            answer = visited;
                            maxScore = score - apeachScore;
                            break;
                        }
                        else if(visited[i] < answer[i]){
                            break;
                        }
                    }
                }
            }
            return;
        }

        if(leftArrow - (infoApeach[depth] + 1) >= 0){
            int[] newVisited = newArray(visited);
            newVisited[depth] = infoApeach[depth] + 1;
            if(infoApeach[depth] > 0){
                isLionWin(depth + 1, newVisited, leftArrow - (infoApeach[depth] + 1), score + 10 - depth, (apeachScore - (10 - depth) >= 0 ? apeachScore - (10 - depth) : 0));
            }
            else {
                isLionWin(depth + 1, newVisited, leftArrow - (infoApeach[depth] + 1), score + 10 - depth, apeachScore);
            }
        }
        isLionWin(depth + 1, visited, leftArrow, score, apeachScore);
    }

    private static int[] newArray(int[] visited) {
        int[] newArray = new int[visited.length];

        int cnt = 0;
        for(int i : visited){
            newArray[cnt++] = i;
        }

        return newArray;
    }
}
