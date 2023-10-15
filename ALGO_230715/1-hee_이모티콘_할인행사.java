import java.util.*;

class Solution {
    
    // 4가지 경우에 대한 dfs로 경우의 수를 던진다.
    
    private static class SaleInfo {
        int price, ratio;
        
        public SaleInfo(int price, int ratio){            
            this.price = price;            
            this.ratio = ratio;
        }
        
        @Override
        public String toString(){
            return "SaleInfo = [ price = "+this.price+", ratio = "+this.ratio+" ]";
        }
        
    }
    
    private static int N;
    public int[] solution(int[][] users, int[] emoticons) {
        
        N = emoticons.length;
        sales = new SaleInfo[N];
        dfs(0, emoticons, users);    
        return new int[]{maxSubscribe, maxSale};
    }

    private static SaleInfo[] sales;
    private static float[] disCount = new float[] {0.6f, 0.7f, 0.8f, 0.9f};
    private static int[] disRatio = new int[] {40, 30, 20, 10};
    // 세일 정보에 대한 경우의 수를 생성하는 dfs 메서드!
    private static void dfs(int cnt, int[] emoticons, int[][] users){
        if(cnt == N) {
            // System.out.println(Arrays.toString(sales));
            findMax(sales, users);
            return;
        }
        for(int i = 0; i < 4; i ++){
        
            sales[cnt] = new SaleInfo(
                (int) (emoticons[cnt]*disCount[i]),
                disRatio[i]
            );
            
            dfs(cnt+1, emoticons, users);
        }
    }
    
    // 세일 정보를 기준을 사용자 배열을 돌면서,
    // 고객마다 구매한 가격과 이모티콘 플러스 구독 여부를 판정해준다.
    
    private static int maxSale; // 최대 매출액 
    private static int maxSubscribe; // 최대 구독 수    
    
    private static void findMax(SaleInfo[] sales, int[][] users){
        
        int saleValue = 0;
        int subValue = 0;
        
        for(int[] arr : users){
            // int id = arr[0];
            int limitRatio = arr[0];
            int boundaryPrice = arr[1];            
            int purchaseValue = 0; // 사용자별 구매금액 저장할 변수
            
            for(SaleInfo si : sales){
                if(si.ratio < limitRatio) continue; // 할인율 x면 매출 안하므로 !;
                purchaseValue += si.price; // 아니면 구매!                
            }
            // 전체 값에 반영
            if(purchaseValue >= boundaryPrice) {
                subValue++; // 임계지점을 넘었으면 구독
            }else {//아니라면 매출 집계
                saleValue += purchaseValue;            
            }            
        }
        
        // 두 가지 조건!
        if(subValue > maxSubscribe){ // 구독자수가 더 높은가? 최상위 비교조건
            maxSubscribe = subValue;
            maxSale = saleValue;
            
        }else if(subValue == maxSubscribe) { // 구독자 수가 같은가? 
            if(saleValue>maxSale){ // 매출 갱신했나?
                maxSubscribe = subValue;
                maxSale = saleValue;                
            }
            
        } // else 절의 조건은 비교 할 필요 X
        
        
        
    }
    
}