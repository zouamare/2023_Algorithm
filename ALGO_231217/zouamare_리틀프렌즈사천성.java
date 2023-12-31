package coding_test.AlgoY2023.M12.D17;

import java.util.*;

class 리틀프렌즈사천성 {
    public String solution(int m, int n, String[] board) {
        Set<Character> set = new TreeSet<>();
        char[][] map = new char[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = board[i].charAt(j);
                if(board[i].charAt(j) != '.' && board[i].charAt(j) != '*'){
                    // 빈칸 또는 막힌 칸이 아니면..
                    set.add(board[i].charAt(j));
                }
            }

        }

        // TreeSet에는 현재 존재하는 알파벳이 다 들어가 있을 것.
        boolean[] visited = new boolean[set.size()];
        boolean check = true;	// 변화가 있는지 체크
        StringBuilder ans = new StringBuilder();

        while(check){
            int idx = 0;
            check = false;
            in: for(char c : set){
                if(!visited[idx] && removeBlock(map, c, m, n)){
                    visited[idx] = true;
                    ans.append(c);
                    check = true;
                    break in;
                }
                idx++;
            }
        }

        if(ans.length() < set.size()){
            return "IMPOSSIBLE";
        }

        return ans.toString();
    }

    private boolean removeBlock(char[][] map, char c, int m, int n){
        int x = 0, y = 0;
        int[] dx = { -1, 0, 1, 0};
        int[] dy = { 0, 1, 0, -1};
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[m][n][4];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] == c){
                    x = i;
                    y = j;

                    queue.add(new int[]{x, y, -1, -1});	// x축, y축, 방향 인덱스, 방향 변경 횟수
                    visited[x][y][0] = true;
                    visited[x][y][1] = true;
                    visited[x][y][2] = true;
                    visited[x][y][3] = true;

                    while(!queue.isEmpty()){
                        int[] temp = queue.poll();

                        if(c == 'G' && temp[0] == 4 && temp[1] == 2){
                            System.out.println(temp[0]+ " " + temp[1] + " " + temp[2] + " " + temp[3]);
                            System.out.println(visited[4][3][1]);
                        }
                        if(temp[3] > 1){
                            continue;
                        }

                        for(int d = 0; d < 4; d++){
                            int nx = temp[0] + dx[d];
                            int ny = temp[1] + dy[d];

                            if(nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny][d] && (map[nx][ny] == '.' || map[nx][ny] == c)){

                                if(map[nx][ny] == c && ((temp[2] != d && temp[3] < 1) || (temp[2] == d && temp[3] < 2))){
                                    map[x][y] = '.';
                                    map[nx][ny] = '.';
                                    return true;
                                }
                                if(temp[2] == d){
                                    // 처음이거나 방향이 같으면
                                    visited[nx][ny][d] = true;
                                    queue.offer(new int[]{nx, ny, d, temp[3]});
                                }
                                else{
                                    if(temp[3] + 1 < 2){
                                        visited[nx][ny][d] = true;
                                        queue.offer(new int[]{nx, ny, d, temp[3] + 1});
                                    }

                                }
                            }
                        }

                    }

                    break;
                }
            }
        }
        return false;
    }
}