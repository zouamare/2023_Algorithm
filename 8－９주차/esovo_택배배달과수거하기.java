package week8_9;

public class esovo_택배배달과수거하기 {

	public static void main(String[] args) {
		long a = solution(4, 5, new int[] { 1, 0, 3, 1, 2 }, new int[] { 0, 3, 0, 4, 0 });
		System.out.println(a);
	}

	public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;

		int del_n = 0;
		int pick_n = 0;

		// 처음 시작할 가장 먼 배달/수거 위치 구하기
		for(int i=n-1; i>=0; i--) {
			if(deliveries[i]!=0 || pickups[i]!=0) {
				del_n = pick_n = i+1;
				break;
			}
		}

		while (true) {
			if(del_n==0 && pick_n==0) break;
			
			// 배달
			int c = cap;
			while (del_n > 0 && c > 0) {
				if (deliveries[del_n - 1] <= c) {
					c -= deliveries[del_n - 1];
					deliveries[del_n - 1] = 0;
					del_n--;
					while(del_n>0 && deliveries[del_n-1] == 0) del_n--;
				} 
				else {
					deliveries[del_n - 1] -= c;
					c = 0;
				}
			}

			// 수거
			if(del_n==0) c = 0;
			while (pick_n > 0 && c < cap) {
				if (pickups[pick_n - 1] <= (cap - c)) {
					c += pickups[pick_n - 1];
					pickups[pick_n - 1] = 0;
					pick_n--;
					while(pick_n > 0 && pickups[pick_n-1] == 0) pick_n--;
				} 
				else {
					pickups[pick_n - 1] -= (cap - c);
					c = cap;
				}
			}

			answer += n*2;
			n = Math.max(del_n, pick_n);
		}

		return answer;
	}
}
