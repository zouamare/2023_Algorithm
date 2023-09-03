package coding_test.AlgoY2023.M09.D03;

public class 단체사진_찍기 {
    char[] Friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    final int MAX_FRIENDS_COUNT = Friends.length;
    int now_n, count;
    String[] now_data;
    public int solution(int n, String[] data) {
        this.count = 0;
        this.now_n = n;
        this.now_data = data;

        Permutation(0, new char[MAX_FRIENDS_COUNT], new boolean[MAX_FRIENDS_COUNT]);

        return this.count;
    }

    private void Permutation(int depth, char[] result, boolean[] visited) {
        if(depth >= MAX_FRIENDS_COUNT){
            boolean flag = true;
            for(int i = 0; i < now_n; i++){
                if(!flag)   break;
                char friend1 = now_data[i].charAt(0);
                char friend2 = now_data[i].charAt(2);
                char comp = now_data[i].charAt(3);
                int num = Character.getNumericValue(now_data[i].charAt(4));

                int friend1_idx = 0, friend2_idx = 0;
                for(int j = 0; j < MAX_FRIENDS_COUNT; j++){
                    if(result[j] == friend1){
                        friend1_idx = j;
                    }
                    if(result[j] == friend2){
                        friend2_idx = j;
                    }
                }

                int dist = Math.abs(friend1_idx - friend2_idx) - 1;
                switch (comp){
                    case '=':   // 같음
                        if(num != dist){
                            flag = false;
                        }
                        break;
                    case '<':   // 미만
                        if(dist >= num){
                            flag = false;
                        }
                        break;
                    case '>':   // 초과
                        if(dist <= num){
                            flag = false;
                        }
                        break;
                }
            }
            if(flag)    count++;
            return;
        }

        for(int i = 0; i < MAX_FRIENDS_COUNT; i++){
            if(visited[i])  continue;
            result[depth] = Friends[i];
            visited[i] = true;
            Permutation(depth + 1, result, visited);
            visited[i] = false;
        }
    }
}
