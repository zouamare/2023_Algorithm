import java.util.*;

class Node implements Comparable<Node>{
    int x, y, no;
    Node left, right;

    public Node(int x, int y , int no){
        this.x = x;
        this.y = y;
        this.no = no;
    }

    @Override
    public int compareTo(Node o){
        return this.y==o.y ? this.x-o.x : o.y-this.y;
    }
}

class Solution {

    static int[][] result;
    static int idx;

    // 삽입, 제일 중요한 부분!
    public void add(Node parent, Node child){
        // 지금 아래의 조건식이 가능한 이유는
        // 1. 주어진 테스트케이스에서 동일한 x 값이 없다는 전제조건과
        // 2. 이를 이용해 정렬이 가능했으므로 정렬된 상태라는
        // 두 가지 전제 조건이 있으므로, 아래의 조건식으로 이진 트리로 분기가 가능하기 때문이다.
        if(parent.x > child.x){ // 자식노드가 왼쪽 편으로 가야한다면?
            if(parent.left == null) parent.left = child; // null이다? -> 비었다!, 여기다!
            else add(parent.left, child); // 왼쪽 종단을 찾아서 출발~~
        }else {
            if(parent.right == null) parent.right = child; // null이다? -> 비었다!, 여기다!
            else add(parent.right, child); // 오른쪽 종단을 찾아서 출발~~
        }
    }



    // 전위순회
    public void preorder(Node root){
        if(root != null){
            result[0][idx++] = root.no;
            preorder(root.left);
            preorder(root.right);
        }
    }

    // 후위순회
    public void postorder(Node root){
        if(root != null){
            postorder(root.left);
            postorder(root.right);
            result[1][idx++] = root.no;
        }
    }


    public int[][] solution(int[][] nodeinfo) {

        // 2차원 배열을 노드를 담은 배열로 변환해준다
        Node[] nodes = new Node[nodeinfo.length];

        for(int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        }
        // add 메서드에 적힌 주석의 전제조건처럼 정렬을 필수로 해줘야하고,
        // 정렬의 기준은 Node 클래스의 문제에 주어진 조건에 따라 Comparable로 구현했다.
        Arrays.sort(nodes);

        // 트리의 root를 만든다.
        Node root = nodes[0];
        for(int i = 1; i < nodes.length; i++) {
            add(root, nodes[i]);
        }

        result = new int[2][nodeinfo.length];
        idx = 0;
        preorder(root);
        idx = 0;
        postorder(root);

        return result;
    }
}