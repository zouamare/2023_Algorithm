package coding_test.AlgoY2023.M03.D08;

import java.util.*;
/*
1) combination을 이용한 방식
- 처음에 수학적으로 접근하려고 하니 어떻게 풀어야 할지 막막했다. 완탐 이외의 방식으로 풀려고 했다.
- 그러나 배열의 최대 크기가 8인 것에서 바로 완탐으로 풀어야 겠다고 생각을 바꿨다.
- 완탐으로 Permutaion(순열) 방식으로 진행한 후 중복 제거는 HashSet을 이용했다.
- 풀이 시간: 약 40분
*/
class PG_불량사용자 {
    static String[] u_id;
    static String[] b_id;
    static int count;
    static Set<String> dupliCheck;
    public static int solution(String[] user_id, String[] banned_id) {
        u_id = user_id;
        b_id = banned_id;
        dupliCheck = new HashSet<>();

        Comb(0, 0, new boolean[u_id.length]);

        return count;
    }

    private static void Comb(int idx, int depth, boolean[] visited){
        if(depth == b_id.length){

            if(dupliCheck.add(visitedToString(visited))){
                count++;
            }

            return;
        }
        for(int i = 0; i < u_id.length; i++){
            if(visited[i] || !isNotValid(i, depth))  continue;
            visited[i] = true;
            Comb(i + 1, depth+1, visited);
            visited[i] = false;
        }
    }

    private static boolean isNotValid(int u_idx, int b_idx){
        if(u_id[u_idx].length() != b_id[b_idx].length()){   // 길이가 다르면 안됨
            return false;
        }
        for(int i = 0; i < b_id[b_idx].length(); i++){
            if(b_id[b_idx].charAt(i) == '*')    continue;
            if(b_id[b_idx].charAt(i) != u_id[u_idx].charAt(i))  return false;
        }
        return true;
    }

    private static boolean[] copyArr(boolean[] copyArr){
        boolean[] temp = new boolean[copyArr.length];
        for(int i = 0; i < copyArr.length; i++){
            if(copyArr[i])  temp[i] = true;
        }
        return temp;
    }

    private static String visitedToString(boolean[] visited){
        StringBuilder sb = new StringBuilder();
        for(boolean value : visited){
            if(value){
                sb.append("O");
            }
            else{
                sb.append("X");
            }
        }
        return sb.toString();
    }
}