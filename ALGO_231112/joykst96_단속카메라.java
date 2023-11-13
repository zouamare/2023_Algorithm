import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a1, a2) -> Integer.compare(a1[0], a2[0]));
        int anker = Integer.MIN_VALUE;
        int camera = 0;
        for (int[] route: routes) {
            if (anker < route[0]) {
                anker = route[1];
                ++camera;
            } else {
                if (anker > route[1]) anker = route[1];
            }
        }
        return camera;
    }
}