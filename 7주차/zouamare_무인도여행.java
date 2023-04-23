package coding_test.AlgoY2023.M04.D23;

import java.util.*;

// 맨 처음에는 DFS로 풀었는데 범위의 총 합은 BFS로 풀어야 한다는 것을 늦게 알았다.
public class 무인도여행 {
    int maxX, maxY;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    ArrayList<Integer> answer;
    boolean[][] visited;
    char[][] map;
    public int[] solution(String[] maps) {

        maxX = maps.length;
        maxY = maps[0].length();

        answer = new ArrayList<>();

        visited = new boolean[maxX][maxY];
        map = new char[maxX][maxY];

        for(int i = 0; i < maxX; i++){
            for(int j = 0; j < maxY; j++){
                map[i][j] = maps[i].charAt(j);
            }
        }
        // DFS로 숫자를 탐색함
        // 값을 ArrayList에 넣는다.
        // 정렬하고 배열로 변환하여 반환함.

        for(int i = 0; i < maxX; i++){
            for(int j = 0; j < maxY; j++){
                if(!visited[i][j] && map[i][j] != 'X'){
                    visited[i][j] = true;
                    GetIslandCount(i, j);
                }
            }
        }

        if(answer.isEmpty()){
            return new int[] { -1 };
        }

        return answer.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    private void GetIslandCount(int x, int y){
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{x, y});
        int sum = 0;
        sum += Character.getNumericValue(map[x][y]);

        while(!queue.isEmpty()){
            int[] data = queue.poll();

            for(int d = 0; d < 4; d++){
                int nx = data[0] + dx[d];
                int ny = data[1] + dy[d];

                if(nx >= 0 && nx < maxX && ny >= 0 && ny < maxY && !visited[nx][ny] && map[nx][ny] != 'X'){
                    visited[nx][ny] = true;
                    sum += Character.getNumericValue(map[nx][ny]);
                    queue.add(new int[]{nx, ny});
                }
            }

        }

        answer.add(sum);
    }
}

