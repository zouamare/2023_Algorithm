package week7;

import java.util.List;
import java.util.ArrayList;

public class esovo_모음사전 {

	public static void main(String[] args) {
		int s = solution("AAAAE");
		System.out.println(s);
	}

	static List<String> words;
	static char[] alp = { 'A', 'E', 'I', 'O', 'U' };

	static public int solution(String word) {
		words = new ArrayList<>();

		for (int i = 0; i < alp.length; i++) {
			dfs(String.valueOf(alp[i]));
		}

		int answer = 0;
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).equals(word)) {
				answer = i + 1;
				break;
			}
		}

		return answer;
	}

	static private void dfs(String str) {
		if (str.length() > 5)
			return;
		if (!words.contains(str))
			words.add(str);
		for (int i = 0; i < alp.length; i++) {
			dfs(str + alp[i]);
		}
	}

}
