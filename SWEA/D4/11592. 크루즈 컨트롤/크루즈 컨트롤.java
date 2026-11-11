import java.io.*;
import java.util.*;

public class Solution {
	
	static int[][] horses;
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
            
        	int D = Integer.parseInt(st.nextToken());
        	int N = Integer.parseInt(st.nextToken());
        	
        	horses = new int[N][2];
        	for(int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j = 0; j < 2; j++) {
        			horses[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	double maxFinishTime = 0;
        	
        	for(int i = 0; i < N; i++) {
        		int remainDist = D - horses[i][0];
        		double finishTime = (double)remainDist / (double)horses[i][1];
        		maxFinishTime = Math.max(maxFinishTime, finishTime);
        	}
        	
        	double speed = (double)D / maxFinishTime;
        	
            sb.append('#').append(test_case).append(' ');
            sb.append(String.format("%.9g", speed));
            sb.append('\n');
        }
        System.out.print(sb);
    }
}