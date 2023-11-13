import java.util.*;

class Solution {
    
    static List<HashSet<Integer>> set;
    public int solution(int N, int number) {
        if(N == number) return 1;
        
        set = new ArrayList<>();
        set.add(new HashSet<>());
        set.get(0).add(N);
        for(int i=1; i<8; i++){
            set.add(new HashSet<>());
            int value = N;
            for(int j=0; j<i; j++) value = value*10+N;
            set.get(i).add(value);
            
            for (int j=0; j<i; j++){ 
                for (int k=0; k<i; k++){ 
                    if (j+k+1 == i) {
                        for (int a : set.get(j)) { 
                            for (int b : set.get(k)) { 
                                set.get(i).add(a+b); 
                                set.get(i).add(a*b);
                                if (a-b > 0) set.get(i).add(a-b); 
                                if (a/b > 0) set.get(i).add(a/b); 
                            } 
                        }   
                    } 
                } 
            }
            
            if(set.get(i).contains(number)) return i+1;
        }
        
        return -1;
    }
}