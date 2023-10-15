package coding_test.AlgoY2023.M04.D30;

public class 택배배달과수거하기 {
    public static void main(String[] args) {
        System.out.println(solution(2, 7, new int[]{1, 0, 2, 0, 1, 0, 8}, new int[] {0, 2, 0, 1, 0, 2, 0}));
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int deliver = 0, pickup = 0;
        long answer = 0;

        for(int i = n-1; i >= 0; i--){
            if(deliveries[i] != 0 || pickups[i] != 0){
                int cnt = 0;
                while(deliver < deliveries[i] || pickup < pickups[i]){
                    cnt++;
                    deliver += cap;
                    pickup += cap;
                }
                deliver -= deliveries[i];
                pickup -= pickups[i];
                answer += (i+1) * cnt * 2L;
            }
        }
        return answer;
    }

//    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
//        int maxDeliveryLoc = -1;
//        int maxPickupLoc = -1;
//
//        // 배달할 최장거리와 수거할 최장거리를 파악한다.
//
//        // 뒤에서 부터 그리디하게 구해본다.
//
//        for(int i = n - 1; i >= 0; i--){
//            if(maxDeliveryLoc == -1 && deliveries[i] > 0){
//                maxDeliveryLoc = i;
//            }
//            if(maxPickupLoc == -1 && pickups[i] > 0){
//                maxPickupLoc = i;
//            }
//            if(maxDeliveryLoc != -1 && maxPickupLoc != -1){
//                break;
//            }
//        }
//
//        long sum = 0;
//        int dCount = 0;
//        int pCount = 0;
//
//        while(maxDeliveryLoc > 0 || maxPickupLoc > 0){
//            if(maxDeliveryLoc > maxPickupLoc){
//                sum += (maxDeliveryLoc + 1) * 2L;
//            }
//            else{
//                sum += (maxPickupLoc + 1) * 2L;
//            }
//            while(dCount < cap && maxDeliveryLoc >= 0) {
//                if (deliveries[maxDeliveryLoc] + dCount <= cap) {
//                    dCount += deliveries[maxDeliveryLoc];
//                    deliveries[maxDeliveryLoc] = 0;
//                    if(maxDeliveryLoc == 0 && deliveries[maxDeliveryLoc] == 0){
//                        break;
//                    }
//                    while(maxDeliveryLoc > 0 && deliveries[--maxDeliveryLoc] == 0){}
//                } else {
//                    deliveries[maxDeliveryLoc] -= cap - dCount;
//                    dCount += cap - dCount;
//                }
//            }
//
//            while(pCount < cap && maxPickupLoc >= 0) {
//                if (pickups[maxPickupLoc] + pCount <= cap) {
//                    pCount += pickups[maxPickupLoc];
//                    pickups[maxPickupLoc] = 0;
//                    if(maxPickupLoc == 0 && pickups[maxPickupLoc] == 0){
//                        break;
//                    }
//                    while(maxPickupLoc > 0 && pickups[--maxPickupLoc] == 0){}
//                } else {
//                    pickups[maxPickupLoc] -= cap - pCount;
//                    pCount += cap - pCount;
//                }
//            }
//
//            dCount = 0;
//            pCount = 0;
//        }
//
//        return sum;
//    }
}
