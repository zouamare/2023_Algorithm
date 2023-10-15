import java.util.*;

public class P1 {
    public int[] solution(String msg) {
        Map<Integer, Integer> dict = new HashMap<>();
        List<Integer> ret = new ArrayList<>();
        int idx = 0;
        for (int i = 'A'; i <= 'Z'; ++i) {
            dict.put(String.valueOf((char)i).hashCode(), ++idx);
        }
        StringBuilder sb = new StringBuilder();
        int prev = -1;
        for (int i = 0; i < msg.length(); ++i) {
            sb.append(msg.charAt(i));
            if (!dict.containsKey(sb.toString().hashCode())) {
                dict.put(sb.toString().hashCode(), ++idx);
                ret.add(prev);
                sb.delete(0, sb.length() - 1);
            }
            prev = dict.get(sb.toString().hashCode());
        }
        ret.add(prev);
        return ret.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        P1 p = new P1();
        System.out.println(Arrays.toString(p.solution("KAKAO")));
        System.out.println(Arrays.toString(p.solution("TOBEORNOTTOBEORTOBEORNOT")));
        System.out.println(Arrays.toString(p.solution("ABABABABABABABAB")));
    }
}
