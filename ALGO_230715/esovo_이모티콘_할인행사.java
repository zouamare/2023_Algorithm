class Solution {
    
    static int N, max_join, max_cost;
	static int[] input = {10, 20, 30, 40};
	static int[] numbers, emoticon;
	static int[][] user;
	
    public int[] solution(int[][] users, int[] emoticons) {
        
        // 1. 이모티콘의 할인율 중복 순열 구하기
        N = emoticons.length;
        max_join = 0;
        max_cost = 0;
        numbers = new int[N];
        emoticon = emoticons;
        user = users;
        perm(0, 0);
        
        int[] answer = {max_join, max_cost};
        return answer;
    }
	
	private void perm(int cnt, int flag){ 
		if(cnt == N) {
			// 각 사용자별 이모티콘 구매 및 이모티콘 플러스 서비스 가입
			int join = 0;
			int costs = 0;
			outer: for(int i=0; i<user.length; i++) {
				int cost = 0;
				for(int j=0; j<numbers.length; j++) {
					// 사용자가 원하는 할인율보다 높거나 같은 경우 -> 이모티콘 구매
					if(user[i][0] <= numbers[j]) {
						cost += emoticon[j]-emoticon[j]*numbers[j]*0.01;
						// 최대 가격 넘어가면 이모티콘 플러스 가입
						if(cost>=user[i][1]) {
							join++;
							cost = 0;
							continue outer;
						}
					}
					else continue;
				}
				costs += cost;
			}
			
			if(join > max_join) {
				max_join = join;
				max_cost = costs;
			}
			else if(join == max_join) {
				if(costs > max_cost)
					max_cost = costs;
			}
			return;
		}
		
		for(int i = 0; i < 4; i++) { // 선택지
			numbers[cnt] = input[i];
			perm(cnt+1, flag | 1<<i);
		}
	}
}