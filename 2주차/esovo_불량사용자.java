package day0307;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution_불량사용자 {

	public static void main(String[] args) {
		int s = solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"fr*d*", "*rodo", "******", "******"});
		System.out.println(s);
	}

	static int bsize, usize;
	static ArrayList<Integer>[] id_list;
	static HashSet<HashSet<Integer>> id_set;
	public static int solution(String[] user_id, String[] banned_id) {
        
		// 각 불량 사용자 아이디에 해당하는 아이디 구하기
		usize = user_id.length;
		bsize = banned_id.length;
		id_list = new ArrayList[bsize];
		
		for(int i=0; i<bsize; i++) {
			id_list[i] = new ArrayList<>();
			
			/*
			 *  정규 표현식 사용!
			 *  ^: 문자열의 시작
			 *  $: 문자열의 끝
			 *  $ 안넣었더니 실행 시 에러 발생
			 *  ^ 안넣었더니 테케 6, 7, 9, 11 실패해서 추가하니까 통과
			 */
			Pattern pattern = Pattern.compile("^"+banned_id[i].replace("*", ".")+"$");
			for(int j=0; j<usize; j++) {
				Matcher matcher = pattern.matcher(user_id[j]);
				if(matcher.find()) id_list[i].add(j);
			}
		}
		
		id_set = new HashSet<>();
 		comb(new HashSet<Integer>(), 0);
		
        return id_set.size();
    }
	
	// 불량 사용자가 될 수 있는 아이디 조합 생성
	private static void comb(HashSet<Integer> set, int order) {
		if(order == bsize) {
			id_set.add(set);
			return;
		}
		
		for(int i=0; i<id_list[order].size(); i++) {
			int num = id_list[order].get(i);
			if(set.contains(num)) continue;
		
			set.add(num);
			comb(new HashSet<>(set), order+1);
			set.remove(num);
		}
	}
	
}
