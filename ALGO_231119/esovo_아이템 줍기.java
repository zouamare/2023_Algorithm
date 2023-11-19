import java.util.*;

class Solution {
    
    static int[][] rectangles;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        rectangles = rectangle;
        int[][] map = new int[101][101];
        boolean[][] visited = new boolean[101][101];
        for(int i=0; i<rectangle.length; i++){
            for(int x=rectangle[i][0]*2; x<=rectangle[i][2]*2; x++) map[x][rectangle[i][1]*2] = map[x][rectangle[i][3]*2] = 1;
            for(int y=rectangle[i][1]*2; y<=rectangle[i][3]*2; y++) map[rectangle[i][0]*2][y] = map[rectangle[i][2]*2][y] = 1;
        }
        
        int answer = 0;
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {characterX*2, characterY*2});
        visited[characterX*2][characterY*2] = true;
        
        outer: while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i=0; i<size; i++){
                int[] loc = queue.poll();
                if(loc[0]==itemX*2 && loc[1]==itemY*2) break outer;
                for(int d=0; d<4; d++){
                    int dx = loc[0]+dr[d];
                    int dy = loc[1]+dc[d];
                    if(dx<0 || dy<0 || dx>100 || dy>100 || visited[dx][dy] || map[dx][dy]==0) continue;
                    if(isIn(dx, dy)) continue;
                    visited[dx][dy] = true;
                    queue.add(new int[] {dx, dy});
                }
            }
            answer++;
        }
        
        return answer/2;
    }
    
    private boolean isIn(int x, int y){
        for(int i=0; i<rectangles.length; i++){
            if(x>rectangles[i][0]*2 && x<rectangles[i][2]*2 && y>rectangles[i][1]*2 && y<rectangles[i][3]*2) return true;
        }
        return false;
    }
    
}