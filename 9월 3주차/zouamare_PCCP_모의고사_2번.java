package coding_test.AlgoY2023.M09.D17;

public class PCCP모의고사2 {
    int[] dx = { 0,  1, 0, -1};     // 0: +y, 1: +x, 2: -y, 3: -x
    int[] dy = { 1 , 0, -1, 0};     // 0: +y, 1: +x, 2: -y, 3: -x

    public int[] solution(String command) {
        int x = 0, y = 0, dir = 0;    // 초기 방향 +y축을 향하여 셋팅

        for(int i = 0; i < command.length(); i++){
            // string foreach 안되나?
            // error: for-each not applicable to expression type 발생
            switch(command.charAt(i)){
                case 'R':
                    dir = (dir + 1) % 4;
                    break;
                case 'L':
                    dir = ((dir + 4) - 1) % 4;
                    break;
                case 'G':
                    x = x + dx[dir];
                    y = y + dy[dir];
                    break;
                case 'B':
                    x = x - dx[dir];
                    y = y - dy[dir];
                    break;
            }

        }

        return new int[]{x, y};
    }
}
