package coding_test.AlgoY2023.M10.D15;

import java.util.*;

public class 베스트앨범 {
    // [우선순위]
    // 1. 속한 노래가 많이 재생된 장르
    // 2. 장르 내에서 많이 재생된 노래
    // 3. 고유 번호가 낮은 노래

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500})));
    }

    // 장르 별로 가장 많이 재생된 노래를 두 개씩
    public static int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> playCntMap = new HashMap<>();
        Map<String, PriorityQueue<Song>> songPQ = new HashMap<>();

        for(int i = 0; i < genres.length; i++){
            if(!playCntMap.containsKey(genres[i])){
                // 처음 등록하는 장르

                // 1. 장르별 재생 수 집계
                playCntMap.put(genres[i], plays[i]);
                songPQ.put(genres[i], new PriorityQueue<>(new Comparator<Song>() {
                    @Override
                    public int compare(Song s1, Song s2) {
                        return s1.playCnt - s2.playCnt; // playCnt minHeap
                    }
                }));

                // 2. 장르 내 재생 수 정렬
                PriorityQueue<Song> PQ = songPQ.get(genres[i]);
                PQ.add(new Song(i, plays[i]));
            }
            else{
                // 이미 등록된 장르

                // 1. 장르별 재생 수 집계
                playCntMap.replace(genres[i], playCntMap.get(genres[i]) + plays[i]);

                // 2. 장르 내 재생 수 정렬
                PriorityQueue<Song> PQ = songPQ.get(genres[i]);
                PQ.add(new Song(i, plays[i]));
                if(PQ.size() > 2){
                    PQ.poll();
                }

            }
        }

        PriorityQueue<Genre> genrePQ = new PriorityQueue<>(new Comparator<Genre>() {
            @Override
            public int compare(Genre g1, Genre g2) {
                return g2.plays - g1.plays;     // plays maxHeap
            }
        });
        ArrayList<Integer> answer = new ArrayList<>();

        for(Map.Entry<String, Integer> entry : playCntMap.entrySet()){
            genrePQ.add(new Genre(entry.getKey(), entry.getValue()));
        }

        while(!genrePQ.isEmpty()){
            PriorityQueue<Song> PQ = songPQ.get(genrePQ.poll().name);
            Song s1 = null, s2 = null;

            // 기존 PQ가 minHeap이어서 반대로 삽입하기 위한 로직
            if(!PQ.isEmpty()) s1 = PQ.poll();
            if(!PQ.isEmpty()) s2 = PQ.poll();

            if(s2 != null) answer.add(s2.oriNum);
            if(s1 != null) answer.add(s1.oriNum);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    static class Song{
        int oriNum;
        int playCnt;

        public Song(int oriNum, int playCnt){
            this.oriNum = oriNum;
            this.playCnt = playCnt;
        }
    }

    static class Genre{
        String name;
        int plays;

        public Genre(String name, int plays){
            this.name = name;
            this.plays = plays;
        }
    }
}
