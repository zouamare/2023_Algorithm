import java.util.*;

class Solution {

    static class Point implements Comparable<Point>{
        int x, y, type;
        
        public Point(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
        
        @Override
        public int compareTo(Point p){
            if(this.x == p.x){
                if(this.y == p.y) return this.type - p.type;
                return this.y - p.y;
            }
            return this.x - p.x;
        }
        
        @Override
        public boolean equals(Object obj){
            Point p = (Point) obj;
            return x==p.x && y==p.y && type==p.type;
        }
    }
    
    static List<Point> list;
    
    public int[][] solution(int n, int[][] build_frame) {
        
        list = new ArrayList<>();
                
        for(int i=0; i<build_frame.length; i++){
            int x = build_frame[i][0]; // 가로 좌표
            int y = build_frame[i][1]; // 세로 좌표
            int a = build_frame[i][2]; // 구조물 종류
            int b = build_frame[i][3]; // 설치, 삭제 여부
            Point p = new Point(x, y, a);
            
            if(a == 0){ // 기둥
                if(b == 1 && isZero(x, y)) list.add(p);
                else{
                    list.remove(p);
                    if(!check()) list.add(p);
                }
            }
            else{ // 보
                if(b == 1 && isOne(x, y)) list.add(p);
                else{
                    list.remove(p);
                    if(!check()) list.add(p);
                }
            }
        }
        
        
        int[][] answer = new int[list.size()][3];
        Collections.sort(list);
        for(int i=0; i<list.size(); i++) {
            answer[i] = new int[] {list.get(i).x, list.get(i).y, list.get(i).type};
        }
        return answer;
    
    }
    
    private boolean isZero(int x, int y){
        return y==0 || list.contains(new Point(x, y-1, 0)) || list.contains(new Point(x, y, 1)) || list.contains(new Point(x-1, y, 1));
    }
    
    private boolean isOne(int x, int y){
        return list.contains(new Point(x, y-1, 0)) || list.contains(new Point(x+1, y-1, 0)) || (list.contains(new Point(x-1, y, 1)) && list.contains(new Point(x+1, y, 1)));
    }
    
    private boolean check(){
        for(Point p : list){
            int x = p.x;
            int y = p.y;
            int type = p.type;
            
            if(type == 0){
                if(!isZero(x, y)) return false;
            }
            else{
                if(!isOne(x, y)) return false;
            }
        }
        return true;
    }
}