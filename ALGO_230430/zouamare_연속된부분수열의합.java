package coding_test.AlgoY2023.M04.D30;

public class 연속된부분수열의합 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 1, 2, 3, 4, 5}, 5));
    }

    /*
    * 처음에는 아무 생각없이 점핏 강연보면서 n^2 방식으로 풀려고 했으나,,
    * 시간이 100만이라면 n^2은 100억이기에 슬라이딩 윈도우로 방식을 변경해서 풀었습니다.
    * */
    public static int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        answer[0] = 0;
        answer[1] = 1000000;
        int sum = 0;
        int start = 0, end = 0;

        while(start < sequence.length){
            if(sum < k){
                if(end < sequence.length){
                    sum += sequence[end++];
                }
                else{
                    break;
                }
            }
            else if(sum > k){
                sum -= sequence[start++];
            }
            else{   // sum == k
                if(answer[1] - answer[0] > end - start - 1){
                    answer[0] = start;
                    answer[1] = end - 1;
                }
                sum -= sequence[start++];
            }
        }

        return answer;
    }
}
