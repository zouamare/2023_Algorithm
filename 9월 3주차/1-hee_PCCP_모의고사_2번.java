class Solution {    
    
    // 로봇 클래스를 만들고,
    // 로봇의 방향에 대한 값을 관리할 vector 값도 init
    // 초기 로봇은 무조건 +y 축 방향을 보고 있으므로, 0~3까지 시계방향으로 인덱스를 부여함
    // 명령 중 회전이 주어지므로 시계방향 또는 반시계로 함
    
    static class Robot{
        int x, y, vector; // vector [0, 1, 2, 3] y, x, -y, -x
        public Robot(int x, int y){
            this.x = x;
            this.y = y;
        }
                    
        /*
        'R': 로봇이 오른쪽으로 90도 회전합니다.
        'L': 로봇이 왼쪽으로 90도 회전합니다.
        'G': 로봇이 한 칸 전진합니다.
        'B': 로봇이 한 칸 후진합니다.
        */
        
        // 명령을 처리하는 로봇의 메서드 move
        void move(char c){ // 명령어 c를 기준으로 로봇을 움직인다.
            switch(c){
                case 'R': // 오른쪽 회전
                    this.vector = (this.vector+1)%4;
                    break;
                case 'L': // 왼쪽 회전 (무한루프 하도록!)
                    int size = 4;
                    this.vector = (size+this.vector-1)%size; // 일반식
                    break;
                case 'G': // 직진
                    this.x += dx[this.vector];
                    this.y += dy[this.vector];                    
                    break;
                case 'B': // 후진
                    this.x -= dx[this.vector];
                    this.y -= dy[this.vector];
                    break;
            }
        }
        
        // 로봇 좌표 찍어볼 toString() 메서드
        @Override
        public String toString(){
            return "Robot = [ x = "+this.x+", y = "+this.y+" ]";
        }        
    }
    // 로봇의 움직임에 대해 관리할 벡터 배열
    static int[] dx = new int[]{0,1,0,-1};
    static int[] dy = new int[]{1,0,-1,0};
    
    public int[] solution(String command) {
        
        // 로봇 객체 생성
        Robot rb = new Robot(0,0);        
        // 명령어를 주어지는데로 수행
        for(int i = 0, size = command.length(); i < size; i ++){
            char cmd = command.charAt(i); // 문자열에 담긴 명령어 추출
            rb.move(cmd); // 로봇 move
        }
        
        // 최종적인 로봇의 위치를 리턴
        int[] answer = {rb.x, rb.y};
        return answer;
    }
}