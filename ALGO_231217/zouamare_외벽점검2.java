package coding_test.AlgoY2023.M12.D17;

// 블로그 참고 [https://gre-eny.tistory.com/168]
public class 외벽점검2 {
    private int resultMinCount, n;
    private int[] weak, dist, spreadWeak;
    private boolean finish;
    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        this.resultMinCount = Integer.MAX_VALUE;
        init();

        for(int i = 1; i <= dist.length; i++) {
            Permutation(0, i, new boolean[dist.length], new int[i]);
        }

        if(resultMinCount == Integer.MAX_VALUE) return -1;
        return resultMinCount;
    }

    private void init(){
        int size = weak.length;
        this.spreadWeak = new int[size * 2 - 1];

        for(int i = 0; i < size; i++){
            spreadWeak[i] = weak[i];
        }

        for(int i = 0; i < size - 1; i++){
            spreadWeak[i + size] = weak[i] + n ;
        }
    }

    private void Permutation(int depth, int maxDepth, boolean[] visited, int[] result) {
        if(finish){
            return;
        }
        if(depth == maxDepth){
            checkWeakArea(result);
            return;
        }
        for(int i = 0; i < dist.length; i++){
            if(!visited[i]){
                visited[i] = true;
                result[depth] = dist[i];
                Permutation(depth+1, maxDepth, visited, result);
                visited[i] = false;
            }
        }
    }

    private void checkWeakArea(int[] result) {
        for(int i = 0; i < weak.length; i++){
            int start = i;
            boolean flag = true;

            for(int j = 0; j < result.length; j++) {
                for(int k = i; k < i + weak.length; k++){
                    if(spreadWeak[k] - spreadWeak[start] > result[j]){
                        start = k;
                        j++;

                        if(j == result.length){
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag){
                    resultMinCount = j + 1;
                    finish = true;
                    return;
                }
            }

        }
    }
}
