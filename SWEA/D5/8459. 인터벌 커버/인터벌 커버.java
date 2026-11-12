import java.io.*;
import java.util.*;

public class Solution {
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int X = Integer.parseInt(st.nextToken());
        	int N = Integer.parseInt(st.nextToken());
        	
        	
        	int arr[][] = new int[N][2];
        	
        	for(int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		arr[i][0] = Integer.parseInt(st.nextToken());
        		arr[i][1] = Integer.parseInt(st.nextToken());
        	}
        
        	Arrays.sort(arr, (o1, o2) -> {
        		if(o1[0] == o2[0]) return o1[1] - o2[1];
        		else return o1[0] - o2[0];
        	});
        	

        	long dp[] = new long[X + 1];
        	dp[0] = 1;
        	for(int i = 0; i < N; i++) {
        		int s = arr[i][0];
        		int e = arr[i][1];
        		for(int j = X; j >= s - 1; j--) {
        			if(j >= e) dp[j] = dp[j] * 2 % 1_000_000_007;
        			else dp[e] = (dp[e] + dp[j]) % 1_000_000_007;
        		}
        	}
        	
            sb.append('#').append(test_case).append(' ');
            sb.append(dp[X]);
            sb.append('\n');
        }
        System.out.print(sb);
    }
}