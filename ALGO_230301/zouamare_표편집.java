package coding_test.AlgoY2023.M03.D01;

import java.util.Stack;
/*
* 1) boolean[] check 배열을 이용한 풀이
* - 되돌릴 때는 용이했지만 이동할 때 해당 위치가 이동 가능한지 여부를 체크할 때 Brute Force 방식으로 접근함
* - 따라서 효율성 체크에서 마지막 세 개 테케가 시간초과가 발생함
* 2) Deque를 이용한 풀이
* - 해당 위치가 이동 가능한지 여부를 파악하기는 쉬우나 Z의 경우에 시간 복잡도가 증가할 것 같아 시도 도중 중단
* 3) prev, next를 저장하는 배열을 이용한 풀이 => BEST!!
* - 한 인덱스의 앞, 뒤 정보를 저장하여 이를 이용한 풀이
* - 되돌릴 때, 이동 가능한 지 여부 파악 모두 용이함
* */
public class zouamare_표편집 {
    public static void main(String[] args) {
        String result = solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"});
        System.out.println(result);
    }

    // https://moonsbeen.tistory.com/294 참고
    public static String solution(int n, int k, String[] cmd) {
        int[] pre = new int[n]; // idx의 이전 값을 나타내는 배열
        int[] next = new int[n];    // idx의 다음 값을 나타내는 배열
        int num;
        for(int i = 0; i < n; i++) {
            pre[i] = i - 1;     // i의 이전 값을 저장
            next[i] = i + 1;    // i의 다음 값을 저장
        }
        next[n - 1] = -1;   // 맨 매지막 값의 다음은 없으니 -1 저장

        Stack<Node> stack = new Stack<>();
        StringBuilder sb = new StringBuilder("O".repeat(n));    // 다 O으로 채워 놓고 시작
        for(String command : cmd) {
            switch (command.charAt(0)){
                case 'U':
                    num = Integer.parseInt(command.split(" ")[1]);
                    while(num-- > 0) {
                        k = pre[k];
                    }
                    break;
                case 'D':
                    num = Integer.parseInt(command.split(" ")[1]);
                    while(num-- > 0) {
                        k = next[k];
                    }
                    break;
                case 'C':
                    stack.push(new Node(pre[k], k, next[k]));
                    if(pre[k] != -1) next[pre[k]] = next[k];    // 현재 노드 삭제 후 앞뒤 연결
                    if(next[k] != -1) pre[next[k]] = pre[k];    // 아래가 -1이 아니라면
                    sb.setCharAt(k, 'X');

                    if(next[k] != -1) k = next[k];
                    else k = pre[k]; //마지막 행인 경우에 바로 윗 행 선택
                    break;
                case 'Z':
                    Node node = stack.pop();
                    if(node.pre != -1) next[node.pre] = node.cur; //연결 정보 복구
                    if(node.nxt != -1) pre[node.nxt] = node.cur;
                    sb.setCharAt(node.cur, 'O');
                    break;
            }
        }
        return sb.toString();
    }

    public static class Node{
        int pre, cur, nxt;

        public Node(int pre, int cur, int nxt) {
            this.pre = pre;
            this.cur = cur;
            this.nxt = nxt;
        }
    }

// 아래 방식은 시간 복잡도 문제 발생..
//    static int idx;
//    static boolean[] check;
//    public static String solution(int n, int k, String[] cmd) {
//        Stack<Integer> recentDeletedIdx = new Stack<>();
//        check = new boolean[n];
//        idx = k;
//        int times;
//
//        for(String command : cmd){
//            switch (command.charAt(0)){
//                case 'U':
//                    moveUp(Integer.parseInt(command.split(" ")[1]));
//                    break;
//                case 'D':
//                    moveDown(Integer.parseInt(command.split(" ")[1]));
//                    break;
//                case 'C':
//                    recentDeletedIdx.add(idx);
//                    check[idx] = true;
//                    int result = moveDown(1);
//                    if(result == -1){
//                        moveUp(1);
//                    }
//                    else{
//                        idx = result;
//                    }
//                    break;
//                case 'Z':
//                    check[recentDeletedIdx.pop()] = false;
//                    break;
//            }
//        }
//
//        StringBuilder ans = new StringBuilder();
//        for(int i = 0; i < n; i++){
//            if(check[i])
//                ans.append("X");
//            else
//                ans.append("O");
//        }
//        return ans.toString();
//    }
//
//    private static int moveUp(int count) {
//        int cnt = 0;
//        for(int i = idx - 1; i >= 0; i--){
//            if(!check[i]){
//                if(++cnt == count){
//                    idx = i;
//                    return i;
//                }
//            }
//        }
//        return -1;
//    }
//
//    private static int moveDown(int count){
//        int cnt = 0;
//        for(int i = idx + 1; i < check.length; i++){
//            if(!check[i]){
//                if(++cnt == count){
//                    idx = i;
//                    return i;
//                }
//            }
//        }
//        return -1;
//    }
}
