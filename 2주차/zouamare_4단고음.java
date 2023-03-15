package coding_test.AlgoY2023.M03.D08;
/*
* 맨 끝 2자리는 ++로 이루어져야 하고, 맨 앞은 *로 이루어져 있어야 한다.
* 덧셈기호를 카운팅하면서 주어진 n의 값을 1씩 빼주고, 덧셈이 2개 이상이면서 3으로 나누어 떨어지는 순간이 오면 곱하기(*)가 사용되었다고 생각한다. 이 순간 덧셈기호를 -2 차감시켜주면 된다.
* Math.pow(3, plus / 2) 보다 n이 크다면 만들 수 없는 값이므로 return 0!
* */
class PG_4단고음 {
    public int solution(int n) {
        return solve(n, 0);
    }

    private int solve(int n, int plus){
        int sum = 0;

        if(n == 3 && plus == 2){
            return 1;
        }
        else if(n == 2){
            return 0;
        }
        else if(n == 3){
            return 0;
        }

        if(Math.pow(3, plus / 2) > n){
            return 0;
        }

        if(n % 3 == 0){
            if(plus >= 2){
                sum += solve(n / 3, plus - 2);
            }
            sum += solve(n - 1, plus + 1);
        }
        else{
            sum += solve(n - 1, plus + 1);
        }

        return sum;
    }
}