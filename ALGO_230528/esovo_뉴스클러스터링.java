import java.util.*;

class Solution {

    Map<String, Integer> setA = new HashMap<>(); // A
    Map<String, Integer> setB = new HashMap<>(); // B

    public int solution(String str1, String str2) {
        splitWord(str1, setA);
        splitWord(str2, setB);

        int inter = interWord();
        int union = unionWord();
        System.out.println("inter: " + inter + " union: " + union);

        if(inter==union) return 65536;
        double answer = (double)inter/union*65536;
        System.out.println(answer);
        return (int)answer;
    }

    // 합집합
    private int unionWord(){
        int union = 0;
        Iterator<String> iteratorA = setA.keySet().iterator();
        while (iteratorA.hasNext()) {
            // 집합 A의 key와 value
            String key = iteratorA.next();
            Integer value = setA.get(key);

            // 집합 B에 있는지 여부 판단
            if(setB.containsKey(key)){
                // 있는 경우 개수 비교
                union += Math.max(value, setB.get(key));
                iteratorA.remove(); // setA에서 key 삭제
                setB.remove(key); // setB에서 key 삭제
            }
            else union += value;
        }

        Iterator<String> iteratorB = setB.keySet().iterator();
        while (iteratorB.hasNext()) {
            // 집합 B의 key와 value
            String key = iteratorB.next();
            Integer value = setB.get(key);
            union += value;
        }

        return union;
    }

    // 교집합
    private int interWord(){
        int inter = 0;
        Iterator<String> iterator = setA.keySet().iterator();
        while (iterator.hasNext()) {
            // 집합 A의 key와 value
            String key = iterator.next();
            Integer value = setA.get(key);

            // 집합 B에 있는지 여부 판단
            if(setB.containsKey(key)){
                // 있는 경우 개수 비교
                inter += Math.min(value, setB.get(key));
            }
        }

        return inter;
    }

    // 집합 만들기
    private void splitWord(String str, Map<String, Integer> set){
        for(int i=0; i<str.length()-1; i++){
            String word = str.substring(i, i+2).toUpperCase();
            if(word.matches("^[a-zA-Z]*$")) {
                if(set.containsKey(word)){
                    set.put(word, set.get(word)+1);
                    continue;
                }
                set.put(word, 1);
            }
        }
    }
}