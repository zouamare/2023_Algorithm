package org.ualma.programmers;

import java.util.*;

public class PrivacyPolicy {

    private Map<String, Integer> termsMap = new HashMap<>();
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 0년부터 오늘까지의 일수를 계산
        int now = dateToInt(today);
        // 만료정책 등록
        setTerms(terms);
        // 만료된 개인정보들의 인덱스 + 1 이 들어갈 리스트, 왜 +1 이냐면 문제가 그러라고 했음
        List<Integer> answer = new ArrayList<>();
        // 만료일 계산
        int[] expire = getExpire(privacies);
        // 이거 스트림으로 하고싶었는데 인덱스 뽑으려면 번거로워서 포문으로 처리함
        // 만료일이 현재일보다 작으면 만료된거라고 생각했음
        for (int i = 0; i < expire.length; i++) {
            if (expire[i] < now) answer.add(i + 1);
        }
        // Integer에서 int로 바꿔서 원시배열로 돌려줬음 언박싱 안해주면 에러나더라고
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    // 0년부터 date까지 일수로 바꿔주는 메서드
    private int dateToInt(String date) {
        StringTokenizer st = new StringTokenizer(date, ".");
        int year = Integer.parseInt(st.nextToken()) * 28 * 12;
        int month = Integer.parseInt(st.nextToken()) * 28;
        int day = Integer.parseInt(st.nextToken());
        return year + month + day;
    }

    // 개인정보 보관 만료일을 계산해주는 메서드
    private int[] getExpire(String[] privacies) {
        return Arrays.stream(privacies).mapToInt(s -> {
            StringTokenizer st = new StringTokenizer(s);
            String date = st.nextToken();
            String term = st.nextToken();
            return dateToInt(date) + termsMap.get(term);
        }).toArray();
    }

    // 개인정보 보관 만료정책을 저장하는 메서드
    // 뒤에 -1 해주는건 전날까지로 되어있더라고 그래서 그렇게함
    private void setTerms(String[] terms) {
        for (String term : terms) {
            StringTokenizer st = new StringTokenizer(term);
            String letter = st.nextToken();
            int period = Integer.parseInt(st.nextToken()) * 28 - 1;
            termsMap.put(letter, period);
        }
    }

    public static void main(String[] args) {
        PrivacyPolicy privacyPolicy = new PrivacyPolicy();
        System.out.println(Arrays.toString(
                privacyPolicy.solution(
                        "2022.05.19",
                        new String[] {"A 6", "B 12", "C 3"},
                        new String[] {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"}
                )
        ));
        System.out.println(Arrays.toString(
                privacyPolicy.solution(
                        "2020.01.01",
                        new String[] {"Z 3", "D 5"},
                        new String[] {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"}
                )
        ));
    }
}
