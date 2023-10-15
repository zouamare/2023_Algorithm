package org.ualma.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Clustering {
    public int solution(String str1, String str2) {
        // 전처리 + 토큰화
        String[] tokens1 = tokenize(str1.toLowerCase());
        String[] tokens2 = tokenize(str2.toLowerCase());
        // 카운트 셀 맵
        Map<String, Integer> map = new HashMap<>();
        // 카운트하기
        countFreq(map, tokens1);
        countFreq(map, tokens2);
        // {}와 {}의 교집합으로는 공집합이 있다. 그러니까 아예 원소가 둘다없을경우엔 모두 포함한다 볼수있음.
        if (map.size() == 0) {
            return 65536;
        }
        // 카운트 세어놓은 맵에서 키값쌍 뽑아내서 값이 2이상인것만 추려서 셈
        int count =(int) map.entrySet().stream().filter(m -> m.getValue() >= 2).count();
        // 이제 비율구해서 65536 곱해서 리턴해줌
        return (int) (1.0 * count / map.size() * 65536);
    }

    // 2개씩 자른 리스트를 만들어주는 메서드
    // StringBuilder쓸건데 초기 1글자 넣어두고 뒤에 추가하고 찍어내서 남기고 앞에 빼는식으로 할거임
    // 그런데 이제 영문자 쌍이 아니면 추가는 안하게 할거임
    private String[] tokenize(String str) {
        StringBuilder sb = new StringBuilder();
        List<String> ret = new ArrayList<>();
        sb.append(str.charAt(0));
        for (int i = 1; i < str.length(); ++i) {
            sb.append(str.charAt(i));
            if (isAlpha(sb.toString())) addToken(sb.toString(), ret);
            sb.deleteCharAt(0);
        }
        return ret.toArray(new String[0]);
    }

    // 영문자쌍인지 판단해주는 메서드
    // 심플하게 판별해서 불리언값 리턴한다.
    // 소문자만 고려할건데 왜냐면 입력값을 소문자로 전처리해줬거든
    private boolean isAlpha(String str) {
        for (char ch: str.toCharArray()) {
            if (ch < 'a' || ch > 'z') {
                return false;
            }
        }
        return true;
    }

    // 리스트에 그냥 add하려니까 중복되는 영문자쌍이 비교할 문자열 두개에 여러번씩 들어가있으면
    // HashMap을써서 카운트로 판별하기 난해해지는 문제가 발생함
    // 그래서 나올때마다 숫자를 뒤에 붙이기로했음 숫자는 붙여도 상관없음
    // 왜? 어차피 숫자들어간 문자쌍은 tokenize에서 전부 걸러서 없으니까 써도 상관없음
    private void addToken(String str, List<String> list) {
        int index = 0;
        String token = str + String.valueOf(index);
        while (list.contains(token)) {
            token = str + String.valueOf(++index);
        }
        list.add(token);
    }

    // 리스트받아서 맵에 카운트(키값은 토큰, 값은 카운트) 넣어주는 메서드
    // 근데 사실 카운트는 어차피 1아니면 2니까 불리언값으로 해도 상관없을듯?
    private void countFreq(Map<String, Integer> map, String[] list) {
        for (String str: list) {
            map.computeIfPresent(str, (k, v) -> ++v);
            map.putIfAbsent(str, 1);
        }
    }

    public static void main(String[] args) {
        Clustering clustering = new Clustering();
        System.out.println(
                clustering.solution(
                        "FRANCE",
                        "FRENCH"
                )
        );
        System.out.println(
                clustering.solution(
                        "handshake",
                        "shake hands"
                )
        );
        System.out.println(
                clustering.solution(
                        "aa1+aa2",
                        "AAAA12"
                )
        );
        System.out.println(
                clustering.solution(
                        "E=M*C^2",
                        "e=m*c^2"
                )
        );
    }
}
