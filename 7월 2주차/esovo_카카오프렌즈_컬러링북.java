import java.util.*;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int area = 0;
        int max = 0;
        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        boolean[][] visited = new boolean[m][n];
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(visited[i][j] || picture[i][j] == 0) continue;
                
                area++;
                int cnt = 1;
                int num = picture[i][j];
                visited[i][j] = true;
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[] {i, j});
                
                while(!queue.isEmpty()){
                    int[] q = queue.poll();
                    for(int d=0; d<4; d++){
                        int r = q[0]+dr[d];
                        int c = q[1]+dc[d];
                        
                        if(r>=0 && c>=0 && r<m && c<n && picture[r][c] == num && !visited[r][c]){
                            cnt++;
                            visited[r][c] = true;
                            queue.offer(new int[] {r, c});
                        }
                    }
                }
                max = Math.max(max, cnt);
            }
        }
        
        return new int[] {area, max};
    }
}