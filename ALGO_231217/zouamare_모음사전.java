package coding_test.AlgoY2023.M12.D30;
import java.util.ArrayList;

// 재귀로 만들기
// Map으로 만든다.
// 인자 정리
class 모음사전 {
    ArrayList<String> wordList;
    char[] word = {'A', 'E', 'I', 'O', 'U'};

    public int solution(String word) {
        wordList = new ArrayList<>();

        setList("");

        return wordList.indexOf(word);
    }

    public void setList(String input){
        if(input.length() > 5){
            return;
        }
        wordList.add(input);

        for(int i = 0; i < word.length; i++){
            setList(input + word[i]);
        }
    }
}
