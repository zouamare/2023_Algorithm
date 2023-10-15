package coding_test.AlgoY2023.M10.D15;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 가장큰수 {
    public static void main(String[] args) {
        solution(new int[]{0, 0, 0, 0});
    }

    public static String solution(int[] numbers) {
        PriorityQueue<String> PQ = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int i1 = Integer.parseInt(o1+o2);
                int i2 = Integer.parseInt(o2+o1);

                if(i1 > i2){
                    return 1;
                }
                else if(i1 < i2){
                    return -1;
                }

                return 0;
            }
        });

        for(int i = 0; i < numbers.length; i++){
            PQ.add(String.valueOf(numbers[i]));
        }

        StringBuilder sb = new StringBuilder();
        while(!PQ.isEmpty()){
            sb.append(PQ.poll());
        }

        // [0, 0, 0, 0] => 0000가 아니라 0로 출력되어야 함
        BigInteger bigInteger = new BigInteger(sb.toString());
        return bigInteger.toString();
    }
}
