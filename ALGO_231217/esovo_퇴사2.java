import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+2];

        for(int i=1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int day = i+Integer.parseInt(st.nextToken());
            int benefit = Integer.parseInt(st.nextToken());
            if(day <= n+1){
                dp[day] = Math.max(dp[day], dp[i]+benefit);
            }
            dp[i+1] = Math.max(dp[i+1], dp[i]);
        }

        System.out.println(dp[n+1]);

    }

}