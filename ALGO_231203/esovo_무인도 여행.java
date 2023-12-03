import java.util.*;

class Solution {
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static List<Integer> list;
    public int[] solution(String[] maps) {
        map = new int[maps.length][maps[0].length()];
        for(int i=0; i<maps.length; i++){
            String[] arr = maps[i].split("");
            for(int j=0; j<arr.length; j++){
                if(arr[j].equals("X")) map[i][j] = 0;
                else map[i][j] = Integer.parseInt(arr[j]);
            }
        }
        
        list = new ArrayList<>();
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                if(map[i][j]==0) continue;
                bfs(i, j);
            }
        }
        
        Collections.sort(list);
        if(list.size() == 0) return new int[]{-1};
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) answer[i] = list.get(i);
        return answer;
    }
    
    private void bfs(int x, int y){
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        cnt += map[x][y];
        map[x][y] = 0;
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int i=0; i<4; i++){
                int dx = cur[0]+dr[i];
                int dy = cur[1]+dc[i];
                
                if(dx<0 || dx>=map.length || dy<0 || dy>=map[0].length || map[dx][dy]==0) continue;
                queue.add(new int[]{dx, dy});
                cnt += map[dx][dy];
                map[dx][dy] = 0;
            }
        }
        
        list.add(cnt);
    }
    
}