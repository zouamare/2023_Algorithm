package coding_test.AlgoY2023.M12.D30;

import java.util.Arrays;

public class 셔틀버스2 {
    // 정답
    public String solution(int n, int t, int m, String[] timetable) {
        Arrays.sort(timetable);

        int crw_ptr = 0;
        int pas_cnt = 0;
        int stt_time = 9 * 60;    // 9:00 시작전
        int lst_time = 0;

        for(int i = 0; i < n; i++){
            pas_cnt = 0;
            if(crw_ptr >= timetable.length) break;
            while(pas_cnt < m){
                if(stt_time >= getIntFromTime(timetable[crw_ptr])){
                    lst_time = getIntFromTime(timetable[crw_ptr]);
                    crw_ptr++;
                    pas_cnt++;
                    if(crw_ptr >= timetable.length) break;
                }
                else{
                    break;
                }
            }
            stt_time += t;
        }

        if(pas_cnt < m){
            return getTimeFromInt(540 + (n - 1) * t);
        }
        else {
            return getTimeFromInt(lst_time - 1);
        }
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
