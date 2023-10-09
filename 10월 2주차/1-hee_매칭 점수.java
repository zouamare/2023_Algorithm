import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Solution {
    
	public int solution(String word, String[] pages) {
		
		int numOfPages = pages.length;
		Map<String, Site> pageData = new HashMap<>();		
		Map<String, List<String>> pageLink = new HashMap<>(); // 페이지 주소를 key로 갖는 맵 객체
		
		Pattern pageUrlPattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
		Pattern outUrlPattern = Pattern.compile("<a href=\"(\\S*)\"");
		Pattern wordPattern = Pattern.compile("\\b(?i)"+word+"\\b");
		Matcher matcher;
		
		String home = "";
		String body = "";
		
		for (int i = 0; i < numOfPages; i++) {
			String page = pages[i];
			
			// pageURL 추출
			matcher = pageUrlPattern.matcher(page);
			if (matcher.find()) {
				home = matcher.group(1);
			}
			System.out.println(home); // home url;
            
			// 외부링크 추출
			matcher = outUrlPattern.matcher(page);
			List<String> urlList = new ArrayList<>();
			while (matcher.find()) {
				String out = matcher.group(1);
				urlList.add(out);
			}
			
			pageLink.put(home, urlList); // 외부링크 add
			
			// 본문 추출
			body = page.split("<body>")[1];
			body = body.split("</body>")[0].replaceAll("[0-9]", " ");

			matcher = wordPattern.matcher(body.toLowerCase());
			int basicScore = 0;
			while (matcher.find()) {
				basicScore++;
			}

			pageData.put(home,
					new Site(i, ((double) basicScore / pageLink.get(home).size()), basicScore));
		}

		for (String key : pageLink.keySet()) {
			Site posPage = pageData.get(key); 
			// key를 자신의 주소, value를 외부 링크로 하는 
			// 리스트 객체에 등록된 페이지를 꺼내고
			
			for (String out : pageLink.get(key)) {  
				if(pageData.containsKey(out)) { // 현재 입력값으로 주어진 입력 중에 사이트가 존재하면
					Site outPage = pageData.get(out); // 그 페이지를 꺼내서
					outPage.matScore += posPage.linkScore; // 링크점수를 더해준다.
				}
			}
		}

		List<Site> res = new ArrayList<>(pageData.values());
		Collections.sort(res);
		return res.get(0).index;
		
	}
	

	static class Site implements Comparable<Site> {
		int index;
		int basicScore;
		double linkScore;
		double matScore;

		public Site(int index, double linkScore, int basicScore) {
			this.index = index;
			this.linkScore = linkScore;
			this.basicScore = basicScore;
			this.matScore = basicScore;  // 매칭점수는 무조건 기본점수 + a임!
		}

		@Override
		public int compareTo(Site o) {
			double a = o.matScore-this.matScore;
			if(a==0) {
				return this.index - o.index;
			}else {
				return Double.compare(o.matScore, this.matScore);	
			}
		}
	}
    
}