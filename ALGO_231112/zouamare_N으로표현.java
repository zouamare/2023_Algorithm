package coding_test.AlgoY2023.M11.D11;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

class N으로표현 {
    public int solution(int N, int number) {
        List<Set<Integer>> dpList = new ArrayList<>();
        for(int i = 0; i < 9; i++)
            dpList.add(new HashSet<>());

        dpList.get(1).add(N);

        for(int i = 2; i < 9; i++){
            Set<Integer> nowSet = dpList.get(i);

            for(int j = 1; j <= i; j++){
                Set<Integer> preSet = dpList.get(j);
                Set<Integer> postSet = dpList.get(i - j);
                for(int pre : preSet){
                    for(int post : postSet){
                        nowSet.add(pre + post);
                        nowSet.add(pre - post);
                        nowSet.add(pre * post);
                        if(pre != 0 && post != 0)   nowSet.add(pre / post);
                    }
                }
            }

            nowSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));
        }

        for(Set<Integer> sub : dpList){
            if(sub.contains(number)){
                return dpList.indexOf(sub);
            }
        }

        return -1;
    }
}