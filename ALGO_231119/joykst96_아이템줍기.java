import java.util.*;

class Solution {
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    
    boolean[][] map = new boolean[102][102];
    
    static class Pos {
        int r;
        int c;
        int moveCount;
        
        Pos(int r, int c) {
            this(r, c, 0);
        }
        
        Pos(int r, int c, int moveCount) {
            this.r = r;
            this.c = c;
            this.moveCount = moveCount;
        }
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int startR = characterX * 2;
        int startC = characterY * 2;
        int endR = itemX * 2;
        int endC = itemY * 2;
        for (int[] coordinate: rectangle) {
            fill(coordinate);
        }
        Queue<Pos> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[102][102];
        q.offer(new Pos(startR, startC));
        while (!q.isEmpty()) {
            Pos current = q.poll();
            if (current.r == endR && current.c == endC) {
                return current.moveCount / 2;
            }
            if (isVisited[current.r][current.c]) continue;
            isVisited[current.r][current.c] = true;
            for (int d = 0; d < 4; ++d) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if (nr < 0 || nc < 0 || nr > 100 || nc > 100 || isVisited[nr][nc] || !isRoad(nr, nc)) continue;
                q.offer(new Pos(nr, nc, current.moveCount + 1));
            }
        }
        return 0;
    }
    
    private void fill(int[] coordinate) {
        int r1 = coordinate[0] * 2;
        int c1 = coordinate[1] * 2;
        int r2 = coordinate[2] * 2;
        int c2 = coordinate[3] * 2;
        for (int r = r1; r <= r2; ++r) {
            for (int c = c1; c <= c2; ++c) {
                map[r][c] = true;
            }
        }
    }
    
    static int[] dr8 = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc8 = {-1, 0, 1, -1, 1, -1, 0, 1};
    private boolean isRoad(int r, int c) {
        if (!map[r][c]) return false;
        for (int d = 0; d < 8; ++d) {
            int nr = r + dr8[d];
            int nc = c + dc8[d];
            if (nr >= 0 && nc >= 0 && nr < 102 && nc < 102 && !map[nr][nc]) return true;
        }
        return false;
    }
}