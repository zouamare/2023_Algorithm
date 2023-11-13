import java.util.*;

class Solution {
    static ArrayDeque<Integer> wait = new ArrayDeque<>();
    
    public int[] solution(String[] operations) {
        TreeSet<Integer> tree = new TreeSet<>();
        for (String op: operations) {
            StringTokenizer st = new StringTokenizer(op);
            char command = st.nextToken().charAt(0);
            int parameter = Integer.parseInt(st.nextToken());
            switch (command) {
                case 'I':
                    add(tree, parameter);
                    break;
                case 'D': 
                    delete(tree, parameter);
                    break;
            }
        }
        if (tree.isEmpty()) return new int[]{0, 0};
        return new int[]{tree.last(), tree.first()};
    }
    
    void add(TreeSet<Integer> tree, int parameter) {
        if (tree.contains(parameter)) wait.offer(parameter);
        else tree.add(parameter);
    }
    
    void delete(TreeSet<Integer> tree, int parameter) {
        if (tree.isEmpty()) return;
        if (parameter > 0) tree.pollLast();
        else tree.pollFirst();
        for (int i = 0, j = wait.size(); i < j; ++i) {
            int current = wait.poll();
            if (tree.contains(current)) {
                wait.offerLast(current);
            } else {
                tree.add(current);
                break;
            }
        }
    }

}