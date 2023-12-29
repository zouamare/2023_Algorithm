import java.util.*;

class Solution {
    
    class Node implements Comparable<Node>{
        int x, y, idx;
        Node left, right;
        
        Node(int x, int y, int idx){
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
        
        public int compareTo(Node n){
            if(this.y == n.y) return this.x-n.x;
            return n.y-this.y;
        }
    }
    
    static List<Node> list;
    static int[][] answer;
    static int idx;
    public int[][] solution(int[][] nodeinfo) {
        int N = nodeinfo.length;
        list = new ArrayList<>();
        for(int i=0; i<N; i++) list.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1));
        
        Collections.sort(list);
        
        Node root = list.get(0);
        for(int i=1; i<N; i++) addNode(root, list.get(i));
        
        answer = new int[2][N];
        idx = 0;
        preOrder(root);
        idx = 0;
        postOrder(root);
        
        return answer;
    }
    
    private void addNode(Node parent, Node child){
        if(child.x < parent.x){
            if(parent.left == null) parent.left = child;
            else addNode(parent.left, child);
        } 
        else{
            if(parent.right == null) parent.right = child;
            else addNode(parent.right, child);
        }
    }

    private void preOrder(Node node){
        if(node != null){
            answer[0][idx++] = node.idx;
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private void postOrder(Node node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            answer[1][idx++] = node.idx;
        }
    }
    
}