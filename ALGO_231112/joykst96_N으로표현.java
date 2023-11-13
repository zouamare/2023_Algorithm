import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (N == number) return 1;
        
        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= 8; ++i) {
            dp.add(new HashSet<>());
        }
        dp.get(1).add(N);
        
        for (int i = 2; i <= 8; ++i) {
            int count = 0;
            int repeatNum = N;
            while (++count < i) repeatNum = repeatNum * 10 + N;
            dp.get(i).add(repeatNum);
            
            for (int j = 1; j < i; ++j) {
                int k = i - j;
                for (int num1: dp.get(j)) {
                    for (int num2: dp.get(k)) {
                        dp.get(i).add(num1 + num2);
                        dp.get(i).add(num1 - num2);
                        dp.get(i).add(num1 * num2);
                        if (num2 != 0) {
                            dp.get(i).add(num1 / num2);
                        }
                    }
                }
            }
            if (dp.get(i).contains(number)) return i;
        }
        return -1;
    }
}