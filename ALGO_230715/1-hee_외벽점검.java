import java.util.*;

class Solution {
    private static int MAX = 99999999;
    private static int minCnt;
    private static int N = 0;
    public int solution(int n, int[] weak, int[] dist) {
        // weak to lkList;
        LinkedList<Integer> wkList = new LinkedList<>();
        for(int i : weak) wkList.add(i);
        // init
        N = n;
        minCnt = MAX;

        do{
            makeCase(dist, wkList);
        }while(np(dist));

        return minCnt==MAX? -1 : minCnt;
    }

    private static int countPeople(int[] dist, int[] wkList){
        // init 작업!
        for(int i = 1 ; i < wkList.length; i ++){
            if(wkList[i-1] >= wkList[i]) wkList[i] += N;
        }

        // int wIdx = 0;
        int dIdx = 1;
        int start = wkList[0];
        int end = start + dist[0];
        int cnt = 1;
        int wCnt = 0;

        for(int i = 0 ; i < wkList.length;  i++){
            if(isRangeIn(wkList[i], start, end)){
                wCnt++;
                continue;
            }else {
                if(dIdx >= dist.length) break;
                start = wkList[i--];
                end = start + dist[dIdx++];
            }
        }

        return wCnt != wkList.length ? MAX : dIdx;
    }

    // 범위체크, 현재 친구가 한 번에 점검할 수 있는가?
    private static boolean isRangeIn(int wp, int start, int end){
        if(wp >= start && wp <= end) return true;
        return false;
    }

    private static void makeCase(int[] dist, LinkedList<Integer> wkList){
        int size = wkList.size();
        int cnt = 0;

        // LinkedList를 사용해서 배열을 돌림!
        // 도는 횟수는 배열의 크기만큼만 돌면 모든 경우의 수를 탐색하는 것과 같음
        while(cnt < size){
            int pCnt = countPeople(dist, wkList.stream().mapToInt(Integer::intValue).toArray());
            minCnt = Math.min(pCnt, minCnt);
            wkList.addLast(wkList.removeFirst());
            cnt++;
        }
    }

    // dist에 대한 순열을 빠르게 구하기 위해 np 사용
    private static boolean np(int[] arr) {
        int N = arr.length;
        int i = N-1;
        while(i > 0 && arr[i-1] >= arr[i]) --i;
        if(i==0) return false;
        int j = N-1;
        while(arr[i-1]>= arr[j]) --j;
        swap(arr, i-1, j);
        int k = N-1;
        while(i<k)swap(arr, i++, k--);
        return true;
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}