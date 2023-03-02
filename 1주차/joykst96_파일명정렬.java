package com.java.week1;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class joykst96_파일명정렬 {
    static class Word implements Comparable<Word> {
        /*
            HEAD: 숫자아님, 최소 1글자이상 [^0-9]+
            NUMBER: 숫자임, 최소 1글자이상 최대 5글자 [0-9]{1,5}
            TAIL: 뒤에 암거나(확장자도 싹 긁음) .*
         */
        static final Pattern PATTERN = Pattern.compile("^([^0-9]+)([0-9]{1,5})(.*)");

        // 본문
        String input;
        // 머리
        String head;
        // 몸통
        int number;
        // 꼬리는 정렬에 필요없음
        // 입력 서순
        int order;

        Word(String input, int order) {
            this.input = input;
            this.order = order;
            postConstruct();
        }

        void postConstruct() {
            Matcher matcher = PATTERN.matcher(input);
            if (matcher.find()) {
                // 대소문자 통일
                head = matcher.group(1).toLowerCase();
                // 비교하기편하게 숫자로
                number = Integer.parseInt(matcher.group(2));
            }
        }

        @Override
        public int compareTo(Word o) {
            // 머리먼저
            if (head.equals(o.head)) {
                // 다음 몸통
                if (number == o.number) {
                    // 다음 서순
                    return Integer.compare(order, o.order);
                }
                return Integer.compare(number, o.number);
            }
            return head.compareTo(o.head);
        }
    }

    public String[] solution(String[] files) {
        TreeSet<Word> ret = new TreeSet<>();
        for (int i = 0; i < files.length; ++i) {
            ret.add(new Word(files[i], i));
        }
        // 원본문자열만 뽑아 -> 원시배열로 묶어~
        return ret.stream().map(o -> o.input).toArray(String[]::new);
    }

    public static void main(String[] args) {
        joykst96_파일명정렬 pg = new joykst96_파일명정렬();
        System.out.println(Arrays.toString(pg.solution(
                List.of("img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG").toArray(String[]::new))));
        System.out.println(Arrays.toString(pg.solution(
                List.of("F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat").toArray(String[]::new))));
    }
}
