import java.util.*;

class Solution {

    static class Tile implements Comparable<Tile> {
        int x, y;
        char ch;

        Tile(int x, int y, char ch) {
            this.x = x;
            this.y = y;
            this.ch = ch;
        }

        public int compareTo(Tile t) {
            return this.ch - t.ch;
        }
    }

    static char[][] map;
    public String solution(int m, int n, String[] board) {
        map = new char[m][n];
        List<Tile>[] list = new ArrayList[26];
        boolean[] visited = new boolean[26];
        PriorityQueue<Character> pq = new PriorityQueue<>();
        for(int i=0; i<26; i++) list[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            map[i] = board[i].toCharArray();
            for(int j=0; j<n; j++){
                if(map[i][j]!='*' && map[i][j]!='.') {
                    list[map[i][j]-65].add(new Tile(i, j, map[i][j]));
                    if(!visited[map[i][j]-65]){
                        visited[map[i][j]-65] = true;
                        pq.offer(map[i][j]);
                    } 
                }
            }
        }
        
        Queue<Character> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int size = pq.size();
        int cnt = 0;
        while(!pq.isEmpty()){
            char c = pq.poll();
            Tile t1 = list[c-65].get(0);
            Tile t2 = list[c-65].get(1);
            if(check(t1, t2)){ // 타일을 제거할 수 있는 경우
                cnt++;
                sb.append(c);
                if(cnt == size) return sb.toString();
                map[t1.x][t1.y] = map[t2.x][t2.y] = '.';
                while(!queue.isEmpty()) pq.offer(queue.poll());
            }
            else queue.offer(c);
        }
        
        return "IMPOSSIBLE";
    }

    private boolean check(Tile t1, Tile t2){
        if(t1.y > t2.y){
            if(isColumn(t1.x, t2.y, t1.y, t1.ch) && isRow(t1.x, t2.x, t2.y, t1.ch)) return true;
            if(isColumn(t2.x, t2.y, t1.y, t1.ch) && isRow(t1.x, t2.x, t1.y, t1.ch)) return true;
        }
        else{
            if(isColumn(t1.x, t1.y, t2.y, t1.ch) && isRow(t1.x, t2.x, t2.y, t1.ch)) return true;
            if(isColumn(t2.x, t1.y, t2.y, t1.ch) && isRow(t1.x, t2.x, t1.y, t1.ch)) return true;
        }
        return false;
    }
    
    private boolean isColumn(int r, int c1, int c2, int ch){
        for(int i=c1; i<=c2; i++){
            if(map[r][i]!='.' && map[r][i]!=ch) return false;
        }
        return true;
    }
    
    private boolean isRow(int r1, int r2, int c, int ch){
        for(int i=r1; i<=r2; i++){
            if(map[i][c]!='.' && map[i][c]!=ch) return false;
        }
        return true;
    }
    
}
