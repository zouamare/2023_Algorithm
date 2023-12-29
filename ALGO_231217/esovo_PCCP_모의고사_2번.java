class Solution {
    
    public int[] solution(String command) {
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        
        // 초기 값 세팅
        int x = 0, y = 0;
        int dir = 0;
        
        // 명령어 수행
        char[] commands = command.toCharArray();
        for(int i=0; i<commands.length; i++){
            switch(commands[i]){
                case 'R': // 오른쪽 회전
                    dir = (dir+1)%4;
                    break;
                case 'L': // 왼쪽 회전
                    dir = dir-1<0 ? 3 : dir-1;
                    break;
                case 'G': // 전진
                    x += dc[dir];
                    y += dr[dir];
                    break;
                case 'B': // 후진
                    x -= dc[dir];
                    y -= dr[dir];
                    break;
            }
        }
        
        int[] answer = {x, y};
        return answer;
    }
    
}