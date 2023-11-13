class Solution {
    
    static int N, M;
    static int[][] map;
    
    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        int size = N + M*2 - 2;
        map = new int[size][size];

        for(int i=M-1; i<M-1+N; i++) {
            for(int j=M-1; j<M-1+N; j++)
                map[i][j] = lock[i-(M-1)][j-(M-1)];
        }

        for(int i=0; i<4; i++) {
            key = rotate(key);

            for(int x=0; x<N+M-1; x++) {
                for(int y=0; y<N+M-1; y++) {
                    if(check(key, x, y)) return true;
                }
            }
        }
        
        return false;
    }

    private int[][] rotate(int[][] key) {
        int[][] temp = new int[M][M];
        for(int i=0; i<M; i++) { 
            for(int j=0; j<M; j++) {
                temp[i][j] = key[M-1-j][i];
            }
        }
        return temp;
    }
    
    private boolean check(int[][] key, int x, int y) {
        int[][] temp = new int[map.length][map.length];

        for(int i = 0; i < map.length; i++)
            temp[i] = map[i].clone();

        for(int i=0; i<M; i++) {
            for(int j=0; j<M; j++) {
                if(key[i][j] == 1) {
                    if(x+i<0 || x+i>=N+M-1 || y+j<0 || y+j>=N+M-1) continue;
                    if(temp[x+i][y+j] == 1) return false;
                    temp[x+i][y+j] = 1;
                }
            }
        }

        for(int i=M-1; i<N+M-1; i++) {
            for(int j=M-1; j<N+M-1; j++) {
                if(temp[i][j] == 0)  return false;
            }
        }
        return true;
    }

}