package coding_test.AlgoY2023.M11.D04;

// 백트래킹
// ICN 부터 출발해야 하므로 ICN이 0
// 남은 공항의 수를 집계 => 매개변수로 지정
// 그 이후부터는 처음 나오는 나라면 Array를 추가함
// init으로 몇개의 공항인지 및 map 설정
// 동일한 티켓이 여러장인 경우 확인해야 함

import java.util.*;
import java.util.stream.Collectors;

public class 여행경로 {
    static ArrayList<String> airports;   // key: 도시명, value: 도시 index
    static int[][] map;    // 첫번째 좌표: 출발지, 두번째 좌표: 도착지
    static String[] result;
    static int maxTicketCount;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[][] {{"ICN", "BOO"}, {"ICN", "COO"}, {"COO", "DOO"}, {"DOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"}, {"BOO", "ICN"}, {"COO", "BOO"}})));
    }

    public static String[] solution(String[][] tickets) {
        init(tickets);
        ArrayList<String> route = new ArrayList<>();
        route.add("ICN");
        findRoute(airports.indexOf("ICN"), route, maxTicketCount);
        return result;
    }

    private static void findRoute(int now, ArrayList<String> route, int ticketCount) {
        if(result != null){
            return;
        }
        if(ticketCount == 0){
            result = route.stream().toArray(String[]::new);
            return;
        }
        // 이동할 곳을 찾아야 함
        for(int i = 0; i < map.length; i++){
            if(map[now][i] > 0){
                ArrayList<String> newRoute = copyList(route);
                newRoute.add(airports.get(i));
                map[now][i]--;
                findRoute(i, newRoute, ticketCount - 1);
                map[now][i]++;
            }
        }
    }

    private static ArrayList<String> copyList(ArrayList<String> route) {
        ArrayList<String> newList = new ArrayList<>();
        for(String str : route){
            newList.add(str);
        }
        return newList;
    }

    private static void init(String[][] tickets){
        Set<String> data = new HashSet<>();
        for(int i = 0; i < tickets.length; i++){
            data.add(tickets[i][0]);    // 출발 공항
            data.add(tickets[i][1]);    // 도착 공항
        }

        airports = data.stream().sorted().collect(Collectors.toCollection(ArrayList::new));

        map = new int[airports.size()][airports.size()];
        maxTicketCount = 0;
        for(int i = 0; i < tickets.length; i++){
            map[airports.indexOf(tickets[i][0])][airports.indexOf(tickets[i][1])]++;
            maxTicketCount++;
        }

        result = null;
    }
}
