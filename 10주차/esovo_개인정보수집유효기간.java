import java.lang.*;
import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        /* 
            - YYYY.MM.DD를 숫자로 바꾼다
                - (YYYY-2000)*12*28 + MM*28 + DD
            - 보관 가능한 날짜를 구한다
                - ex) 
                  기준 일자 2022.05.19 = 22*12*28 + 5*28 + 19 = 7551
                  수집 일자 2022.02.19 = 22*12*28 + 2*28 + 19 = 7467
                  유효기간 3달 = 28*3 = 84
                  만료 일자 7467+84 = 7551 이날부터 파기해야 함(이상)
            - 보관 만료 일자보다 크거나 같으면 파기해야할 대상으로 추가
        */    
        
        int day = changeDate(today);
        
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<terms.length; i++){
            StringTokenizer st = new StringTokenizer(terms[i]);
            String alp = st.nextToken();
            int term = Integer.parseInt(st.nextToken())*28;
            map.put(alp, term);
        }
        
        ArrayList<Integer> deletedList = new ArrayList<>();
        for(int i=0; i<privacies.length; i++){
            StringTokenizer st = new StringTokenizer(privacies[i]);
            int priv_day = changeDate(st.nextToken());
            priv_day += map.get(st.nextToken());
            if(priv_day <= day) deletedList.add(i+1);
        }
        
        int[] answer = new int[deletedList.size()];
        for(int i=0; i<answer.length; i++)
            answer[i] = deletedList.get(i);
        return answer;
    }
    
    // 날짜 변환 함수
    private int changeDate(String date){
        StringTokenizer st = new StringTokenizer(date, ".");
        int yyyy = Integer.parseInt(st.nextToken());
        int mm = Integer.parseInt(st.nextToken());
        int dd = Integer.parseInt(st.nextToken());
        int day = (yyyy-2000)*12*28 + mm*28 + dd;
        return day;
    }
}