import java.util.*;

class Solution {
    
    static int R;
    static int row_len, col_len;
    static String[][] relations;
    static List<Integer> columns;
    static List<List<Integer>> answer;

    public int solution(String[][] relation) {
        row_len = relation.length;
        col_len = relation[0].length;
        answer = new ArrayList<>();
        columns = new ArrayList<>();
        relations = relation; 

        for (int i=1; i<=col_len; i++) {
            R = i;
            comb(0, 0);
        }

        return answer.size();
    }

    private void comb(int start, int count) {
        if (count == R) {
            if (isUnique() && isMinimal()) 
                answer.add(new ArrayList<>(columns));
            return;
        }

        for (int i=start; i<col_len; i++) {
            columns.add(i);
            comb(i+1, count+1);
            columns.remove(columns.size()-1);
        }
    }

    private boolean isUnique() {
        Set<String> set = new HashSet<>();

        for (int i=0; i<row_len; i++) {
            StringBuilder sb = new StringBuilder();
            for (int col : columns) sb.append(relations[i][col]);

            String key = sb.toString();
            if (set.contains(key)) return false;
            set.add(key);
        }
        return true;
    }

    private boolean isMinimal() {
        for (List<Integer> column : answer) {
            if (columns.containsAll(column)) return false;
        }
        return true;
    }
}