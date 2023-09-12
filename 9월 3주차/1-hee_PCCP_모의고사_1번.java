import java.util.*;

class Solution {    
    
    // HashMap을 통한 풀이!
    static HashMap<Character, LinkedList<Integer>> dictionary;
    public String solution(String input_string) {
        
        int L = input_string.length(); 
        // 문자열의 문자를 key로 하는 해쉬 맵을 만든다,
        // 해쉬맵의 value는 문자열에서 해당 문자가 위치한 index의 집합이다.
        dictionary = new HashMap<Character, LinkedList<Integer>>();
        
        // 문자열의 길이만큼 반복문을 돌면서
        // key-value 쌍을 만들어준다
        // 값이 없다면 새로 만들고, 있다면 key를 기준으로 값을 추가하는 형태!
        for(int i = 0 ; i < L ; i++ ){
            if(dictionary.get(input_string.charAt(i))==null){
                LinkedList<Integer> lkList = new LinkedList();
                lkList.add(i);
                dictionary.put(input_string.charAt(i), lkList);
            }else {
                dictionary.get(input_string.charAt(i)).add(i);
            }
        }
        
        // 외톨이 문자열을 담을 연결리스트!
        // 알고리즘을 돌면서 추가가 잦을 것이므로 연결리스트 자료구조 선택
        LinkedList<Character> result = new LinkedList<>();

        // 키 값에 대한 for문을 순회
        L:for(char key : dictionary.keySet()){
            LinkedList<Integer> lk = dictionary.get(key);      
            int size = lk.size();
            if(size==1) continue; // 혼자뿐인 알파벳이라면 외톨이가 아니므로 제외           
            lk.sort((o1, o2)->o1-o2); // index를 기준으로 오름차순이 되도록 정렬
            
            for(int i = 0 ; i < size-1 ; i++){ // 현재 + 다음을 비교하며 연속되지 않았는지 확인
                int now = lk.get(i);
                int next = lk.get(i+1);
                boolean cp = Math.abs(now-next) == 1;
                if(!cp) { // 한번이라도 연결이 끊기면 외톨이이므로 반복문 종료 및 결과 기록
                    result.add(key);
                    break;
                }
            }
        }
        
        // result에 저장된 key 값을 이어 붙일 StringBuilder 클래스 선언
        StringBuilder sb = new StringBuilder();
        result.sort((o1, o2)-> o1-o2); // 알파벳도 오름차순으로 해달랬으므로 정렬 한번 더 
        for(char c : result){ // 반복문 돌면서 result에 담긴 문자를 붙임
            sb.append(c);
        }
                
        String answer = sb.toString(); // 정답을 산출하는데        
        return answer.isEmpty() ? "N" : answer; // 빈문자열인지만 체크하여 삼항연산자로 결과 리턴
    }    
}