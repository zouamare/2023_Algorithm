package coding_test.AlgoY2023.M12.D30;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class 순위검색 {
    final static String[] Lang = {"cpp", "java", "python", "-"};
    final static String[] Sect = {"backend", "frontend", "-"};
    final static String[] Carr = {"junior", "senior", "-"};
    final static String[] Food = {"chicken", "pizza", "-"};
    static HashMap<String, ArrayList<Integer>>  candidatesData;

    public static void main(String[] args) {
        solution(new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"}
        ,new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
    }

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        init();

        for(String candidate: info){
            String[] keys = candidate.split(" ");

            // 숫자 정렬
            candidatesData.get(keys[0]+keys[1]+keys[2]+keys[3]).add(Integer.parseInt(keys[4]));

            candidatesData.get("-"+keys[1]+keys[2]+keys[3]).add(Integer.parseInt(keys[4]));
            candidatesData.get(keys[0]+"-"+keys[2]+keys[3]).add(Integer.parseInt(keys[4]));
            candidatesData.get(keys[0]+keys[1]+"-"+keys[3]).add(Integer.parseInt(keys[4]));
            candidatesData.get(keys[0]+keys[1]+keys[2]+"-").add(Integer.parseInt(keys[4]));

            candidatesData.get("--"+keys[2]+keys[3]).add(Integer.parseInt(keys[4]));
            candidatesData.get("-"+keys[1]+"-"+keys[3]).add(Integer.parseInt(keys[4]));
            candidatesData.get("-"+keys[1]+keys[2]+"-").add(Integer.parseInt(keys[4]));
            candidatesData.get(keys[0]+"--"+keys[3]).add(Integer.parseInt(keys[4]));
            candidatesData.get(keys[0]+"-"+keys[2]+"-").add(Integer.parseInt(keys[4]));
            candidatesData.get(keys[0]+keys[1]+"--").add(Integer.parseInt(keys[4]));

            candidatesData.get(keys[0]+"---").add(Integer.parseInt(keys[4]));
            candidatesData.get("-"+keys[1]+"--").add(Integer.parseInt(keys[4]));
            candidatesData.get("--"+keys[2]+"-").add(Integer.parseInt(keys[4]));
            candidatesData.get("---"+keys[3]).add(Integer.parseInt(keys[4]));

            candidatesData.get("----").add(Integer.parseInt(keys[4]));

        }

        sort();

        int idx = 0;
        for(String q : query){
            q = q.replace("and ", "");
            String[] target = q.split(" ");

            ArrayList<Integer> list = candidatesData.get(target[0] + target[1] + target[2] + target[3]);

            int start = 0;
            int end = list.size() - 1;

            while(start <= end){
                int mid = (start + end) / 2;

                if(list.get(mid) < Integer.parseInt(target[4])){
                    start = mid + 1;
                }
                else{
                    end = mid - 1;
                }
            }

            answer[idx] = list.size() - start;
            idx++;
        }

        return answer;
    }

    private static void sort() {
        for(String lang : Lang){
            for(String sect : Sect){
                for(String carr : Carr){
                    for(String food : Food){
                        candidatesData.get(lang+sect+carr+food).sort(Comparator.naturalOrder());
                    }
                }
            }
        }
    }

    private static void init(){
        // 경우의 수 모두 나열
        candidatesData = new HashMap<>();

        for(String lang : Lang){
            for(String sect : Sect){
                for(String carr : Carr){
                    for(String food : Food){
                        candidatesData.put(lang+sect+carr+food, new ArrayList<>());
                    }
                }
            }
        }
    }
}
