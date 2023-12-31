package coding_test.AlgoY2023.M12.D17;

// 블로그 참고 [https://gre-eny.tistory.com/168]
public class 외벽점검 {
    int resultMinCount = Integer.MAX_VALUE;
    int[][] weakArr;
    int N;

    public int solution(int n, int[] weak, int[] dist) {
        weakArr = new int[weak.length][weak.length];
        N = n;

        for(int i = 0; i < weak.length; i++){
            for(int j = 0; j < weak.length; j++){
                weakArr[j][(i + j) % weak.length] = weak[i];
            }
        }


        for(int i = 1; i <= dist.length; i++){
            Permutation(i, 0, dist, new boolean[dist.length], new int[i]);
        }

        if(resultMinCount == Integer.MAX_VALUE) return -1;
        return resultMinCount;
    }

    private void Permutation(int s, int depth, int[] dist, boolean[] visited, int[] result) {
        if(resultMinCount != Integer.MAX_VALUE){
            return;
        }
        if(depth == s){
            if(checkWeakArea(result) && resultMinCount == Integer.MAX_VALUE){
                resultMinCount = s;
            }
            return;
        }
        for(int i = 0; i < dist.length; i++){
            if(!visited[i]){
                visited[i] = true;
                result[depth] = dist[i];
                Permutation(s, depth+1, dist, visited, result);
                visited[i] = false;
            }
        }
    }

    private boolean checkWeakArea(int[] result) {
        for(int i = 0; i < weakArr.length; i++){
            boolean flag = true;
            int idx = 0;
            int length = weakArr[i][0] + result[idx];
            for(int j = 1; j < weakArr[i].length; j++){
                if(weakArr[i][j - 1] > weakArr[i][j]){
                    if(length > N){
                        length = length - N;
                    }
                    else {
                        length = 0;
                    }
                }
                if(length < weakArr[i][j]){
                    if(result.length <= idx + 1){
                        flag = false;
                        break;
                    }
                    else{
                        length = weakArr[j][0] + result[++idx];
                    }
                }
            }
            if(flag){
                return true;
            }
        }

        return false;
    }
}
