import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(int[] priorities, int location) {
        List<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < priorities.length; i++){
            list.add(priorities[i]);
        }
        
        int idx = 0;    // 특정 index 위치 저장할 변수
        int lastIdx = 0;    // 최신 index 위치를 갱신할 변수 (마지막에 idx에 대입하여 이전 i값의 가장 마지막 위치를 확인할 수 있도록 함)
        int count = 1;  // 최종 도출하기 위한 위치 변수
        
        for(int i = 9; i > 0; i--){
            for(int j = idx; j < list.size(); j++){
                if(list.get(j) == i){
                    list.set(j, -1);
                    lastIdx = j;
                    if(j == location){
                        return count;
                    }
                    else{
                        count++;
                    }
                }
            }
            for(int j = 0; j < idx; j++){
                if(list.get(j) == i){
                    list.set(j, -1);
                    lastIdx = j;
                    if(j == location){
                        return count;
                    }
                    else{
                        count++;
                    }
                }
            }
            idx = lastIdx;
        }
        
        return count;
    }
}