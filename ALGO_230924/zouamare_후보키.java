package coding_test.AlgoY2023.M09.D19;

import java.util.*;

public class 후보키 {
    static String[][] relation_copy;
    static ArrayList<boolean[]> minVal;
    static int count;

    public static void main(String[] args) {
        System.out.println(solution(new String[][]{{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}}));
    }

    public static int solution(String[][] relation) {
        relation_copy = relation;
        minVal = new ArrayList<>();

        for(int j = 1; j < relation[0].length; j++){
            Comb(0, new boolean[relation[0].length], j, 0);
        }

        // 중복되는 데이터가 없기때문에 최소값은 1
        return (count == 0 ? 1 : count);
    }

    public static void Comb(int depth, boolean[] visited, int r, int s){
        if(depth >= r){
            HashSet<String> set = new HashSet<>();
            boolean flag = true;
            // relation 이차원 배열의 길이만큼 for문을 돌린다.
            for(int i = 0; i < relation_copy.length; i++){
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < relation_copy[i].length; j++){
                    if(visited[j]){
                        sb.append(relation_copy[i][j]);
                    }
                }
                if(!set.add(sb.toString())){
                    flag = false;
                    break;
                }
            }
            if(flag && isMin(visited)){
                count++;
                minVal.add(visited.clone());
            }
            return;
        }
        for(int i = s; i < relation_copy[0].length; i++){
            if(visited[i])  continue;
            visited[i] = true;
            Comb(depth+1, visited, r, i + 1);
            visited[i] = false;
        }
    }

    private static boolean isMin(boolean[] visited) {
        // 최솟값을 만족하는지 확인
        // minVal을 돌면서 최솟값을 만족하는 것이 있으면 추가하지 않고 중단
        if(minVal.isEmpty())    return true;
        for(int i = 0; i < minVal.size(); i++){
            boolean[] target = minVal.get(i);
            int sCnt = 0, vCnt = 0, tCnt = 0;
            for(int j = 0; j < target.length; j++){
                if(!visited[j] && target[j]){
                    tCnt++;
                }
                else if(visited[j] && !target[j]){
                    vCnt++;
                }
                else if(visited[j] && target[j]){
                    sCnt++;
                }
            }
            if(sCnt > 0 && tCnt == 0 && vCnt > 0){
                return false;
            }
        }

        return true;
    }
}
