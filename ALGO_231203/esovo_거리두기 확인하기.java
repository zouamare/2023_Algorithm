import java.util.*;

class Solution {
    
    class Place{
        int x, y;
        Place(int x, int y){
            this.x = x;
            this.y = y;
        }    
    }
    
    static char[][] map;
    static List<Place>[] list;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public int[] solution(String[][] places) {
        map = new char[5][5];
        list = new ArrayList[5];
        int[] answer = new int[5];
        
        outer: for(int k=0; k<5; k++) {
            list[k] = new ArrayList<>();
            for(int i=0; i<5; i++){
                map[i] = places[k][i].toCharArray();
                for(int j=0; j<5; j++){
                    if(map[i][j] == 'P') list[k].add(new Place(i, j));   
                }
            }
            
            for(int i=0; i<list[k].size()-1; i++){
                if(!check(k, list[k].get(i))) {
                    answer[k] = 0;
                    continue outer;
                }
            }
            answer[k] = 1;
        }

        return answer;
    }
    
    private boolean check(int k, Place place){
        Queue<Place> queue = new LinkedList<>();
        queue.add(place);
        map[place.x][place.y] = '1';
        
        for(int i=0; i<2; i++){
            int size = queue.size();
            for(int j=0; j<size; j++){
                Place p = queue.poll();
                for(int d=0; d<4; d++){
                    int dx = p.x+dr[d];
                    int dy = p.y+dc[d];

                    // 범위를 벗어나거나 이미 방문했거나 파티션인 경우
                    if(dx<0 || dx>=5 || dy<0 || dy>=5 || map[dx][dy]=='1' || map[dx][dy]=='X') continue;
                    if(map[dx][dy] == 'P') return false;
                    queue.add(new Place(dx, dy));
                    map[dx][dy] = '1';
                }   
            }
        }
        return true;
    }
    
}