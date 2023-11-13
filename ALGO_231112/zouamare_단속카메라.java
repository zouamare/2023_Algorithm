package coding_test.AlgoY2023.M11.D11;

import java.util.Arrays;
import java.util.Comparator;

public class 단속카메라 {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{-20,15}, {-14, -5}, {-18,-13}, {-5, -3}}));
    }
    public static int solution(int[][] routes) {
        Pos[] posArr = new Pos[routes.length];
        int camCnt = 0;
        int lastCamPos = -30000;

        for(int i = 0; i < routes.length; i++){
            posArr[i] = new Pos(routes[i][0], routes[i][1]);
        }

        Arrays.sort(posArr, new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {    // 큰 값이 가장 작은 것부터 정렬, 큰 값이 같다면 작은 값이 큰 것부터 정렬
                if(o1.max == o2.max){
                    return o2.min - o1.min;
                }
                return o1.max - o2.max;
            }
        });

        for(int i = 0; i < posArr.length; i++){
            if(lastCamPos < posArr[i].min){
                lastCamPos = posArr[i].max;
                camCnt++;
            }
        }

        return camCnt;
    }

    static class Pos{
        int max;
        int min;

        public Pos(int a, int b){
            this.max = Math.max(a, b);
            this.min = Math.min(a, b);
        }
    }
}
