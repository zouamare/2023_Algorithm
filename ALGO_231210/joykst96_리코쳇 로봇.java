// https://school.programmers.co.kr/learn/courses/30/lessons/169199
import java.util.*;

class Solution {
    static class Location {
        int r;
        int c;
        int count;
        
        Location(int r, int c) {
            this(r, c, 0);
        }
        
        Location(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
        
        Location move(int d) {
            return slide(r, c, d, count);
        }
        
        Location slide(int r, int c, int d, int count) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == 1) return new Location(r, c, count + 1);
            return slide(nr, nc, d, count);
        }
        
        @Override
        public String toString() {
            return String.format("R: %d, C: %d, count: %d", r, c, count);
        }
    }
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    static int R;
    static int C;
    static int[][] map;
    
    public int solution(String[] board) {
        R = board.length;
        C = board[0].length();
        map = new int[R][C];
        Queue<Location> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];
        for (int i = 0; i < R; ++i) {
            map[i] = new int[C];
            for (int j = 0; j < C; ++j) {
                switch(board[i].charAt(j)) {
                    case 'D': map[i][j] = 1; break;
                    case 'G': map[i][j] = 2; break;
                    case 'R': q.offer(new Location(i, j)); break;
                }
            }
        }
        while(!q.isEmpty()) {
            Location current = q.poll();
            // System.out.println(current);
            if (map[current.r][current.c] == 2) {
                return current.count;
            }
            if (visited[current.r][current.c]) continue;
            visited[current.r][current.c] = true;
            
            for (int d = 0; d < 4; ++d) {
                Location next = current.move(d);
                if (visited[next.r][next.c]) continue;
                q.offer(next);
            }
            
        }
        
        return -1;
    }
}