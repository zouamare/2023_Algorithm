package day0301;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class esovo_파일명정렬 {

	public static void main(String[] args) {
		String[] s = solution(
				new String[] { "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG" });
		System.out.println(Arrays.toString(s));
	}

	static class File implements Comparable<File> {
		String head;
		int number;
		String origin;

		public File(String head, int number, String origin) {
			this.head = head;
			this.number = number;
			this.origin = origin;
		}

		@Override
		public int compareTo(File o) {
			if (this.head.equals(o.head)) {
				return Integer.compare(this.number, o.number);
			}
			return this.head.compareTo(o.head);
		}
	}

	private static String[] solution(String[] files) {
		List<File> list = new ArrayList<>();
		
		for (int i = 0; i < files.length; i++) {
			int head_end = 0, number_end = 0, tail_end = files[i].length();
			boolean isHead = false, isNumber = false;
			
			for (int j = 0; j < files[i].length(); j++) {
				if (isNumber) break;
				if (isHead) {
					// 문자인 경우(tail 찾기)
					int num = (int) files[i].charAt(j);
					if (!(num <= 57 && num >= 48)) {
						isNumber = true;
						number_end = j;
					}
				} else {
					// 숫자인 경우(number 찾기)
					int num = (int) files[i].charAt(j);
					if (num <= 57 && num >= 48) {
						isHead = true;
						head_end = j;
					}
				}
			}
			// tail이 빈 문자열인 경우
			if (!isNumber) {
				number_end = tail_end;
			}

			list.add(new File(files[i].substring(0, head_end).toLowerCase(),
					Integer.parseInt(files[i].substring(head_end, number_end)), files[i]));
		}

		Collections.sort(list);
		String[] answer = new String[files.length];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i).origin;
		}
		return answer;
	}

}
