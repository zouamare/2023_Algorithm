package coding_test.AlgoY2023.M12.D30;

import java.util.Arrays;

public class 셔틀버스 {
    // 오답
    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);

        int crw_ptr = 0;
        int sht_cnt = 1;
        int pas_cnt = 0;
        int stt_time = 9 * 60;    // 9:00 시작

        while(sht_cnt <= n && crw_ptr < timetable.length){
            if(stt_time >= getIntFromTime(timetable[crw_ptr]) && pas_cnt + 1 < m){
                pas_cnt++;
            }
            else{
                if(sht_cnt == n){
                    if(pas_cnt + 1 >= m){
                        // 마지막인데 이미 셔틀이 가득찬 상태인 경우!
                        return getTimeFromInt(getIntFromTime(timetable[crw_ptr]) - 1);
                    }
                    else{
                        // pas_cnt < m 보다 작으면
                        return getTimeFromInt(stt_time);
                    }
                }
                while(getIntFromTime(timetable[crw_ptr]) > stt_time){
                    stt_time += t;
                }
                sht_cnt++;
                pas_cnt = 1;
            }
            crw_ptr++;
        }


        return getTimeFromInt((9 * 60) + ((n - 1) * t));
    }

    private int getIntFromTime(String time){
        String[] splitedTime = time.split(":");

        return Integer.parseInt(splitedTime[0]) * 60 + Integer.parseInt(splitedTime[1]);
    }

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
