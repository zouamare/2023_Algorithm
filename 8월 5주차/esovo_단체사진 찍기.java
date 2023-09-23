class Solution {
    
    class Data{
        char ch1, ch2, buho;
        int num;
        
        Data(char ch1, char ch2, char buho, int num){
            this.ch1 = ch1;
            this.ch2 = ch2;
            this.buho = buho;
            this.num = num;
        }
    }
    
    static int answer;
    static Data[] conditions;
    static char[] chars = new char[8];
    static char[] input = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static boolean[] isSelected = new boolean[8];
    
    public int solution(int n, String[] data) {
        answer = 0;
        conditions = new Data[data.length];
        for(int i=0; i<data.length; i++){
            char[] ch = data[i].toCharArray();
            conditions[i] = new Data(ch[0], ch[2], ch[3], ch[4]-'0');
        }
        dfs("", 0);
        return answer;
    }
    
    public void dfs(String order, int cnt){
        if(cnt == 8) {
			// 검증
            boolean isOk = true;
            for(int i=0; i<conditions.length; i++){
                int dist = Math.abs(order.indexOf(conditions[i].ch1)-order.indexOf(conditions[i].ch2))-1;
                if(conditions[i].buho == '='){
                    if(dist != conditions[i].num) {
                        isOk = false;
                        break;
                    }
                }
                else if(conditions[i].buho == '<'){
                    if(dist >= conditions[i].num) {
                        isOk = false;
                        break;
                    }
                }
                else{
                    if(dist <= conditions[i].num) {
                        isOk = false;
                        break;
                    }
                }
            }
            if(isOk) answer++;
			return;
		}
		
		for(int i=0; i<8; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			dfs(order+input[i], cnt+1);
			isSelected[i] = false;
		}
    }
    
}