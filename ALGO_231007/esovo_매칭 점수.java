import java.util.*;
import java.util.regex.*;

class Solution {
    
    class Page implements Comparable<Page> {
        int idx;
        int defaultScore = 0; // 기본 점수
        double linkScore = 0; // 링크 점수
        int linkCount = 0; // 연결된 링크 개수
        String html, url; // 웹 페이지 내용, url

        Page(int idx, String html) {
            this.idx = idx;
            this.html = html;
        }

        @Override
        public int compareTo(Page p) {
            double a = (double) this.defaultScore + this.linkScore;
            double b = (double) p.defaultScore + p.linkScore;

            return Double.compare(b, a);
        }
    }

    private HashMap<String, Page> map = new HashMap<>();
    public int solution(String word, String[] pages) {
        int idx = 0;
        word = word.toLowerCase(); // 검색어 소문자로 변경
        for (int i = 0; i < pages.length; i++) {
            Page page = new Page(idx++, pages[i].toLowerCase()); // 페이지 내용 소문자로 변경

            // 웹 페이지 url 추출
            Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(.+?)\"/>");
            Matcher matcher = pattern.matcher(page.html);
            while (matcher.find()) {
                page.url = matcher.group(1);
            }

            // 검색어가 등장하는 횟수 찾기(기본 점수 구하기)
            int wordIdx = page.html.indexOf(word);
            while (wordIdx != -1) {
                char prev = page.html.charAt(wordIdx - 1);
                char post = page.html.charAt(wordIdx + word.length());

                // 단어 앞 뒤로 영문자가 아닌 경우 -> 단어로 구분되는지 판단
                if (!Character.isLowerCase(prev) && !Character.isLowerCase(post)) {
                    page.defaultScore++;
                }
                wordIdx = page.html.indexOf(word, wordIdx + 1);
            }

            // 외부 링크 수 구하기
            int linkIdx = page.html.indexOf("<a href=");
            while (linkIdx != -1) {
                page.linkCount++;
                linkIdx = page.html.indexOf("<a href=", linkIdx + 1);
            }

            map.put(page.url, page);
        }

        // 링크 점수 계산
        for (Page page : map.values()) {
            Pattern pattern = Pattern.compile("<a href=\"https://(.+?)\">");
            Matcher matcher = pattern.matcher(page.html);
            while (matcher.find()) {
                String externalUrl = matcher.group(1);
                if (map.containsKey(externalUrl)) {
                    map.get(externalUrl).linkScore += (double) page.defaultScore / page.linkCount;
                }
            }
        }

        ArrayList<Page> list = new ArrayList<>(map.values());
        Collections.sort(list);

        return list.get(0).idx;
    }
    
}
