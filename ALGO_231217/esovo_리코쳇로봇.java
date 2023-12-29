import java.util.*;

class Solution {
    
    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        char[][] map = new char[n][m];
        int startX = 0, startY = 0;
        int endX = 0, endY = 0;
        for(int i=0; i<n; i++){
            map[i] = board[i].toCharArray();
            for(int j=0; j<m; j++){
                if(map[i][j] == 'R'){
                    startX = i;
                    startY = j;
                }
                else if(map[i][j] == 'G'){
                    endX = i;
                    endY = j;
                }
            }
        }
        
        int answer = 0;
        boolean isArrived = false;
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        
        outer: while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int[] q = queue.poll();
                for(int d=0; d<4; d++){
                    int dx = q[0];
                    int dy = q[1];

                    while(dx+dr[d]>=0 && dx+dr[d]<n && dy+dc[d]>=0 && dy+dc[d]<m && map[dx+dr[d]][dy+dc[d]]!='D'){
                        dx += dr[d];
                        dy += dc[d];
                    }
                    if(visited[dx][dy]) continue;
                    if(dx==endX && dy==endY){
                        isArrived = true;
                        break outer;
                    }
                    queue.add(new int[]{dx, dy});
                    visited[dx][dy] = true;
                }
            }
            answer++;
        }
        
        return isArrived ? answer+1 : -1;
    }
    
}