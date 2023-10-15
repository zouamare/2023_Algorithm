package coding_test.AlgoY2023.M07.D08;

import java.util.*;
public class 거리두기확인하기 {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        boolean confirm;

        for(int i = 0; i < 5; i++){ // 대기실 번호
            confirm = true;
            for(int j = 0; j < 5 && confirm; j++){ // 대기실 row
                for(int k = 0; k < 5 && confirm; k++){ // 대기실 col
                    // 사람이 있는 곳인지 아닌지 파악하여 있다면 lookAround 메소드를 호출합니다.

                    if(places[i][j].charAt(k) == 'P'){
                        confirm = lookAround(i, j, k, places);
                    }
                }
            }
            if(confirm){
                // 거리두기를 잘 지킨 경우
                answer[i] = 1;
            }
            else{
                // 거리두기를 잘 지키지 않은 경우
                answer[i] = 0;
            }
        }

        return answer;
    }

    private boolean lookAround(int rn, int x, int y, String[][] place){
        Queue<Pos> queue = new ArrayDeque<>();

        queue.add(new Pos(x, y));

        while(!queue.isEmpty()){
            Pos pos = queue.poll();

            for(int d = 0; d < 4; d++){
                int nx = pos.x + dx[d];
                int ny = pos.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5 && (nx != x || ny != y)){

                    int degree = Math.abs(nx - x) + Math.abs(ny - y);

                    if(degree <= 2 && place[rn][nx].charAt(ny) == 'P'){
                        return false;
                    }
                    else if(degree < 2 && place[rn][nx].charAt(ny) == 'O'){
                        queue.add(new Pos(nx, ny));
                    }
                }
            }

        }
        return true;
    }

    class Pos {
        int x;
        int y;

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
