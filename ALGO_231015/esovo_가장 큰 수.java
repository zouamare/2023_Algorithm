import java.util.*;

class Solution {
    
    public String solution(int[] numbers) {
        
        String[] st = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            st[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(st, new Comparator<String>(){
            public int compare(String o1, String o2){
                return (o2+o1).compareTo(o1+o2);
            }
        });
        
        if(st[0].equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<st.length; i++){
            sb.append(st[i]);
        }
        
        return sb.toString();
    }
}