import java.util.*;

class Solution {
    
    // 일종의 부모 클래스 장르
    // 장르의 이름과, 총 재생 시간, 해당 장르에 포함된 노래(Song) 객체 리스트를 포함
    class Genre implements Comparable<Genre>{        
        String name;
        int totalTime;
        ArrayList<Song> songList;
        
        public Genre(String name){
            this.name = name;
            this.totalTime = 0;
            this.songList = new ArrayList();
        }
        
        void addSong(Song song){ // 노래 추가하고 자동으로 노래 총 재생 시간도 기록하는 메서드
            this.songList.add(song);
            this.totalTime += song.playTime;
        }

        @Override
        public String toString(){
            return 
                "Genre = [ name = " + this.name
                + ", totalTime ="+this.totalTime
                + ", songList = "+songList.toString()+"]";                
        }
        @Override
        public int compareTo(Genre o){ // 장르의 우선순위는 총 재생 시간을 기준으로 함.
            return o.totalTime - this.totalTime;
        }
    }
    
    // 노래 한 곡마다의 정보를 저장할 Song 객체
    class Song implements Comparable<Song>{        
        String genre; // 장르 명
        int playTime, index; // 재생 시간, 노래 번호
        
        public Song(String genre, int playTime, int index){
            this.genre = genre;
            this.playTime = playTime;
            this.index = index;
        }
        
        @Override
        public String toString(){
            return "Song = [ genre = "+this.genre
                +", playTime = "+this.playTime
                +", index = "+this.index+"]";                
        }
        @Override
        public int compareTo(Song o){
            // 정렬을 위한 기준 설정
            int value1 = this.playTime + this.index;
            int value2 = o.playTime + o.index;
            return value2-value1;            
        }
        
    }
    
    public int[] solution(String[] genres, int[] plays) {
        // 장르 별로 가장 많이 재생된 노래를 두 개      
        // 장르는 n개라면 n개 모두 출력하고
        // 각 장르마다 최대 2개씩만 노래 번호를 출력해야함
        // 그러므로 노래 번호는 1 또는 2개 출력
        
        HashMap<String, Genre> genreMap = new HashMap(); // 장르 기록을 위한 map 객체 사용
        
        for(int i = 0, size = genres.length ; i < size ; i++){
            String gName = genres[i];
            int playTime = plays[i];            
            Song song = new Song(gName, playTime, i); // song은 무조건 생성 및 추가해야함!
            
            if(genreMap.get(gName) != null){ // 이전에 장르가 이미 생성되었다면
                genreMap.get(gName).addSong(song); // 해당 장르에 노래만 추가
            } else { // 처음이라면
                Genre g = new Genre(gName); // 장르를 생성하고
                g.addSong(song); // 그 장르에 노래를 추가한 뒤
                genreMap.put(gName, g); // 맵에 장르를 추가해줌
            }            
        }
        
        // 장르의 총 재생 시간에 따라 정렬하고
        // 각 장르에 포함된 Song들의 정렬 작업을 수행
        PriorityQueue<Genre> pque = new PriorityQueue<>();        
        ArrayList<Integer> songIndexs = new ArrayList<>();
        for(Genre g : genreMap.values()){ // 맵 객체에서 장르들을 꺼내온 뒤
            Collections.sort(g.songList, (o1, o2) -> o2.playTime-o1.playTime); // desc 정렬
            pque.offer(g); // 우선순위 큐에 입력
        }

        // 장르별로 하나씩 꺼내면서
        while(!pque.isEmpty()){
            Genre g = pque.poll();
            int max = g.songList.size() < 2 ? g.songList.size() : 2; // 최대 2개씩만
            for(int i = 0 ; i < max ; i ++){
                songIndexs.add(g.songList.get(i).index); // 노래번호를 기입하고
            }
        }
        
        int[] answer  = songIndexs.stream().mapToInt(Integer::intValue).toArray(); // 서터림으로 배열로 만들기!
        return answer;
    }
}