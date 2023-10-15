package week8_9;

import java.util.Arrays;

public class esovo_연속된부분수열의합 {

	public static void main(String[] args) {
		int[] arr = solution(new int[] {1, 2, 3, 4, 5}, 7);
		System.out.println(Arrays.toString(arr));
	}

	static public int[] solution(int[] sequence, int k) {

		int left = 0;
		int right = 0;
		int len = sequence.length + 1;
		int sum = 0;
		int[] answer = new int[2];

		while (right < sequence.length && left <= right) {
			if (left == right)
				sum = sequence[left];
			if (sum == k) {
				if (len > right - left + 1) {
					len = right - left + 1;
					answer[0] = left;
					answer[1] = right;
				}

				sum -= sequence[left];
				if (right + 1 < sequence.length)
					sum += sequence[right + 1];
				if (left == right)
					break;
				left++;
				right++;

			} else if (sum > k) {
				sum -= sequence[left];
				left++;
			} else if (sum < k) {
				if (right + 1 < sequence.length)
					sum += sequence[right + 1];
				right++;
			}
		}

		return answer;
	}
}
