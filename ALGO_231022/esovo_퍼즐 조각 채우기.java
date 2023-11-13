import java.util.*;

public class Solution {
    
    class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    static int len;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public int solution(int[][] game_board, int[][] table) {
        len = game_board.length;
        boolean[][] boardVisited = new boolean[len][len];
        boolean[][] tableVisited = new boolean[len][len];
        Map<Integer, List<int[][]>> boardMap = new HashMap<>();
        Map<Integer, List<int[][]>> tableMap = new HashMap<>();

        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                if(!boardVisited[i][j] && game_board[i][j] == 0) bfs(i, j, 0, game_board, boardVisited, boardMap);
                if(!tableVisited[i][j] && table[i][j] == 1) bfs(i, j, 1, table, tableVisited, tableMap);
            }
        }

        int answer = 0;
        for(int cnt : tableMap.keySet()) {
            for(int[][] puzzle : tableMap.get(cnt)) {
                for(int i=0; i<=3; i++) {
                    puzzle = rotate(puzzle);
                    boolean isChecked = false;

                    if(boardMap.containsKey(cnt)) {
                        for(int[][] board : boardMap.get(cnt)) {
                            if(match(board, puzzle)) {
                                answer += cnt;
                                boardMap.get(cnt).remove(board);
                                isChecked = true;
                                break;
                            }
                        }
                    }
                    if(isChecked) break;
                }
            }
        }
        return answer;
    }

    // 퍼즐 조각 회전
    private int[][] rotate(int[][] puzzle) {
        int[][] temp = new int[puzzle[0].length][puzzle.length];
        for(int i=0; i<puzzle.length; i++) {
            for(int j=0; j<puzzle[0].length; j++) {
                temp[j][puzzle.length-i-1] = puzzle[i][j];
            }
        }
        return temp;
    }

    // 빈칸과 퍼즐이 일치하는지 확인
    private boolean match(int[][] puzzle, int[][] rotatedPuzzle) {
        if(puzzle.length != rotatedPuzzle.length || puzzle[0].length != rotatedPuzzle[0].length) return false;
        for(int i=0; i<puzzle.length; i++) {
            if(!Arrays.equals(puzzle[i], rotatedPuzzle[i])) return false;
        }
        return true;
    }

    // 빈칸 또는 퍼즐을 직사각형으로 변환
    private void moveSquare(List<Point> square, Map<Integer, List<int[][]>> map) {
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;

        for(Point p : square) {
            minRow = Math.min(minRow, p.x);
            maxRow = Math.max(maxRow, p.x);
            minCol = Math.min(minCol, p.y);
            maxCol = Math.max(maxCol, p.y);
        }

        int rowSize = maxRow-minRow+1;
        int colSize = maxCol-minCol+1;

        int[][] gameBoard = new int[rowSize][colSize];

        for(Point p : square) {
            int row = p.x;
            int col = p.y;
            gameBoard[row-minRow][col-minCol] = 1;
        }

        List<int[][]> list = map.getOrDefault(square.size(), new ArrayList<>());
        list.add(gameBoard);
        map.put(square.size(), list);
    }

    // 빈칸 또는 퍼즐 구할 때 사용할 BFS
    private void bfs(int x, int y, int value, int[][] board, boolean[][] visited, Map<Integer, List<int[][]>> map) {
        Queue<Point> queue = new LinkedList<>();
        List<Point> square = new ArrayList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        square.add(new Point(x, y));

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            for(int i=0; i<4; i++) {
                int dx = p.x+dr[i];
                int dy = p.y+dc[i];

                if(dx<0 || dx>=len || dy<0 || dy>=len || visited[dx][dy]) continue;

                if(board[dx][dy] == value) {
                    visited[dx][dy] = true;
                    queue.add(new Point(dx, dy));
                    square.add(new Point(dx, dy));
                }
            }
        }

        moveSquare(square, map);
    }
    
}