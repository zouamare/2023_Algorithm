package coding_test.AlgoY2023.M07.D08;

import java.util.Arrays;

public class 셔틀버스 {
    public String solution(int n, int t, int m, String[] timetable) {
        /*
        [원리설명]
        1) 마지막 셔틀이 최대 인원까지 채워졌을 경우 - 마지막 셔틀의 마지막 사람보다 1분 빨리 도착하면 됨
        2) 마지막 셔틀이 최대 인원까지 채워지지 않았을 경우 - 마지막 셔틀의 출발시각에 도착하면 됨
        */

        Arrays.sort(timetable);

        /*

        [변수 설명]
        - crw_ptr (crew pointer): timetable에서 현재 고려하는 크루의 시간정보 인덱스 위치
        - sht_cnt (shuttle count): 탑승할 셔틀의 갯수
        - pas_cnt (passenger count): 현재 특정 셔틀에 탑승한 크루의 수
        - stt_time (shuttle time): 현재 탑승하려는 셔틀의 출발 시각

         */
        int crw_ptr = 0;
        int sht_cnt = 1;
        int pas_cnt = 0;
        int stt_time = 9 * 60;    // 9:00 시작

        while(sht_cnt <= n && crw_ptr < timetable.length){

            if(stt_time >= getIntFromTime(timetable[crw_ptr]) && pas_cnt < m){
                pas_cnt++;
                crw_ptr++;
            }
            else{
                if(sht_cnt == n){
                    if(pas_cnt >= m){
                        //  1) 마지막 셔틀이 최대 인원까지 채워졌을 경우
                        return getTimeFromInt(getIntFromTime(timetable[crw_ptr - 1]) - 1);
                    }
                    else{
                        // 2) 마지막 셔틀이 최대 인원까지 채워지지 않았을 경우
                        return getTimeFromInt(stt_time);
                    }
                }
                stt_time += t;
                sht_cnt++;
                pas_cnt = 0;
            }

        }

        if(pas_cnt >= m){
            // 1) 마지막 셔틀이 최대 인원까지 채워졌을 경우 (인덱스 끝까지 도달함)
            return getTimeFromInt(getIntFromTime(timetable[crw_ptr - 1]) - 1);
        }

        // 2) 마지막 셔틀이 최대 인원까지 채워지지 않았을 경우 (인덱스 끝까지 도달함)
        return getTimeFromInt(stt_time);
    }

    /*
    * @Method
    * String 타입으로 입력받은 시각을 int 타입으로 변환
    * */
    private int getIntFromTime(String time){
        String[] splitedTime = time.split(":");

        return Integer.parseInt(splitedTime[0]) * 60 + Integer.parseInt(splitedTime[1]);
    }

    /*
     * @Method
     * int 타입으로 입력받은 시각을 String 타입으로 변환
     * */
    private String getTimeFromInt(int time){
        StringBuilder result = new StringBuilder("");

        if(time / 60 < 10){
            result.append("0");
        }
        result.append(time / 60).append(":");
        time %= 60;
        if(time < 10){
            result.append("0");
        }
        result.append(time);

        return result.toString();
    }
}
