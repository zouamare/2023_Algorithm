import java.util.*;

class Solution {
    
    class Music implements Comparable<Music> {
        int num, play;

        Music(int num, int play) {
            this.num = num;
            this.play = play;
        }

        @Override
        public int compareTo(Music m) {
            if (this.play == m.play) return this.num-m.num;
            return m.play-this.play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Music>> genresMap = new HashMap<>();

        // 각 장르별 분류
        for (int i=0; i<genres.length; i++) {
            Music m = new Music(i, plays[i]);
            if (!genresMap.containsKey(genres[i])) {
                genresMap.put(genres[i], new ArrayList<>());
            }
            genresMap.get(genres[i]).add(m);
        }

        // 장르 내림차순
        List<String> sortedGenres = new ArrayList<>(genresMap.keySet());
        sortedGenres.sort(new Comparator<String>() {
            @Override
            public int compare(String g1, String g2) {
                int play1 = 0, play2 = 0;
                for (Music music : genresMap.get(g1)) play1 += music.play;
                for (Music music : genresMap.get(g2)) play2 += music.play;
                return play2-play1;
            }
        });

        // 최대 2개의 곡 수록
        List<Integer> list = new ArrayList<>();
        for (String genre : sortedGenres) {
            List<Music> musicList = genresMap.get(genre);
            Collections.sort(musicList);
            int count = 0;
            for (Music music : musicList) {
                list.add(music.num);
                count++;
                if (count >= 2) break;
            }
        }

        int[] answer = new int[list.size()];
        for (int i=0; i<list.size(); i++) answer[i] = list.get(i);
        
        return answer;
    }
    
}