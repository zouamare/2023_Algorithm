import java.util.*;
import java.util.stream.Stream;

public class P2 {
    static class HR {
        String language;
        String stack;
        String career;
        String food;
        int score;

        HR(String info) {
            StringTokenizer st = new StringTokenizer(info);
            language = st.nextToken();
            stack = st.nextToken();
            career = st.nextToken();
            food = st.nextToken();
            score = Integer.parseInt(st.nextToken());
        }
    }

    static class Query {
        String language;
        String stack;
        String career;
        String food;
        int score;

        Query(String query) {
            StringTokenizer st = new StringTokenizer(query.replaceAll(" and ", " "));
            language = st.nextToken();
            stack = st.nextToken();
            career = st.nextToken();
            food = st.nextToken();
            score = Integer.parseInt(st.nextToken());
        }
    }

    public int[] solution(String[] info, String[] query) {
        List<HR> applies = new ArrayList<>();
        List<Query> queries = new ArrayList<>();
        for (String applicant: info) {
            applies.add(new HR(applicant));
        }
        for (String znjfl: query) {
            queries.add(new Query(znjfl));
        }
        List<Integer> ret = new ArrayList<>();
//        for (Query condition: queries) {
//            ret.add((int)applies.stream()
//                    .filter(a -> condition.language.equals("-") || condition.language.equals(a.language))
//                    .filter(a -> condition.stack.equals("-") || condition.stack.equals(a.stack))
//                    .filter(a -> condition.career.equals("-") || condition.career.equals(a.career))
//                    .filter(a -> condition.food.equals("-") || condition.food.equals(a.food))
//                    .filter(a -> condition.score <= a.score)
//                    .count());
//        }
        for (Query condition: queries) {
            final boolean skipLanguage = condition.language.equals("-");
            final boolean skipStack = condition.stack.equals("-");
            final boolean skipCareer = condition.career.equals("-");
            final boolean skipFood = condition.food.equals("-");
            final boolean skipScore = condition.score == 0;

            Stream<HR> result = applies.stream();

            if (!skipLanguage) {
                result = result.filter(a -> condition.language.equals(a.language));
            }
            if (!skipStack) {
                result = result.filter(a -> condition.stack.equals(a.stack));
            }
            if (!skipCareer) {
                result = result.filter(a -> condition.career.equals(a.career));
            }
            if (!skipFood) {
                result = result.filter(a -> condition.food.equals(a.food));
            }
            if (!skipScore) {
                result = result.filter(a -> condition.score <= a.score);
            }

            ret.add((int)result.count());
        }
        return ret.stream().mapToInt(e -> e).toArray();
    }

    public static void main(String[] args) {
        P2 p = new P2();
        System.out.println(Arrays.toString(p.solution(
                new String[] {
                        "java backend junior pizza 150",
                        "python frontend senior chicken 210",
                        "python frontend senior chicken 150",
                        "cpp backend senior pizza 260",
                        "java backend junior chicken 80",
                        "python backend senior chicken 50"}
                , new String[] {
                        "java and backend and junior and pizza 100",
                        "python and frontend and senior and chicken 200",
                        "cpp and - and senior and pizza 250",
                        "- and backend and senior and - 150",
                        "- and - and - and chicken 100",
                        "- and - and - and - 150"}
        )));

    }
}
