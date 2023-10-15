import java.util.;

class Solution {
    
    static final String[] member = {A, C, F, J, M, N, R, T};
    static boolean[] isVisited;
        
    public int solution(int n, String[] data) {           
        isVisited = new boolean[member.length];        
        cnt = 0;
        dfs(, data);        
        int answer = cnt;
        return answer;
    }
    
    static int cnt;
    public void dfs(String names, String[] data){
        if(names.length()==member.length){  만들어진 순번의 길이가 전체 멤버수와 같다면
            if(check(names, data)) cnt++;  유효한 순번인지 체크하고 카운트 계수 ++
            return;
        }
        for(int i = 0 ; i  8 ; i ++){  dfs로 조합
            if(!isVisited[i]){
                isVisited[i] = true;
                String name = names + member[i];
                dfs(name, data);
                isVisited[i] = false;                
            }
        }        
        
    }
    
    public boolean check(String name, String[] data){ 
         dfs에서 만들어지는 경우에 대해서
         주어진 조건의 길이만큼 검사를 유효한지 검사한다.
        
        for(String x  data){
            
            int pos1 = name.indexOf(x.charAt(0)); 
            int pos2 = name.indexOf(x.charAt(2));
                        
            char order = x.charAt(3);  제약 타입
            int limit = x.charAt(4) - '0';  제약거리
                                    
             제약에 따른 분기
            if(order == '=') {                
                if(Math.abs(pos1-pos2)!=limit+1) return false;
            }else if(order == '') {
                if(Math.abs(pos1-pos2) = limit+1) return false;                
            }else if(order == ''){
                if(Math.abs(pos1-pos2) = limit+1) return false;
            }
        }
        
        return true;
    }
}