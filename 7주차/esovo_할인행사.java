package week7;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class esovo_할인행사 {

	public static void main(String[] args) {
		int s = solution(new String[]{"banana", "apple", "rice", "pork", "pot"}, new int[] {3, 2, 2, 2, 1}, new String[] {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"});
		System.out.println(s);
	}

	static public int solution(String[] want, int[] number, String[] discount) {
		List<String> window = new ArrayList<>();
		// 슬라이딩 윈도우 초기 설정
		for (int i = 0; i < 10; i++) {
			window.add(discount[i]);
		}

		// 문자열로 들어오는 할인 품목 처리하기 위한 HashMap
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < want.length; i++) {
			map.put(want[i], i);
		}

		// 정현이가 원하는 제품 수량의 합
		int total = 10;

		// 슬라이딩 윈도우를 위해 초기에 가리키는 곳 지정
		int start = 0;
		int end = 9;

		// 원하는 물건을 각각 몇 개씩 살 수 있는지 여부 확인
		int[] buy = new int[number.length];
		for (int i = 0; i < total; i++) {
			if (map.containsKey(discount[i])) {
				buy[map.get(discount[i])]++;
			}
		}

		// 원하는 물건을 전부 구매했는지 확인 후 갱신
		int answer = 0;
		while (true) {
			boolean check = true;
			for (int i = 0; i < number.length; i++) {
				if (number[i] > buy[i]) {
					check = false;
					break;
				}
			}
			if (check)
				answer++;
			if (end + 1 == discount.length)
				break;

			if (map.containsKey(discount[start])) {
				buy[map.get(discount[start])]--;
			}

			start++;
			end++;

			if (map.containsKey(discount[end])) {
				buy[map.get(discount[end])]++;
			}
		}
		return answer;
	}
}
