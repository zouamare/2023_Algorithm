package coding_test.AlgoY2023.M09.D17;

public class PCCP모의고사1 {
    final static int ALPHA_CNT = 26;

    public static void main(String[] args) {
        System.out.println(solution("eeddee"));
    }

    public static String solution(String input_string) {
        boolean[] alpha = new boolean[ALPHA_CNT];
        boolean[] answer = new boolean[ALPHA_CNT];

        for(int i = 0; i < input_string.length(); i++){
            if(alpha[input_string.charAt(i) - 'a']
                    && input_string.charAt(i-1) != input_string.charAt(i)){
                // 외톨이 알파벳
                answer[input_string.charAt(i) - 'a'] = true;
            }
            else{
                if(!alpha[input_string.charAt(i) - 'a']){
                    alpha[input_string.charAt(i) - 'a'] = true;
                }
                else{
                    // 뭉쳐 있으므로 상관이 없음
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < ALPHA_CNT; i++){
            if(answer[i]){
                sb.append((char)('a' + i));
            }
        }

        return (sb.toString().equals("")? "N" : sb.toString());
    }
}
