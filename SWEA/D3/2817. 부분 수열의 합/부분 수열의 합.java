import java.io.*;
import java.util.*;

public class Solution {
	
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	int K = Integer.parseInt(st.nextToken());
        	
        	int[] arr = new int[N];
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N; i++) {
        		arr[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	
            sb.append('#').append(test_case).append(' ');
            sb.append(dfs(N, K, 0, arr, 0));
            sb.append('\n');
        }
        System.out.print(sb);
    }
    
    public static int dfs(int N, int K, int depth, int[] arr, int sum) {
    	if(sum == K) {
    		return 1;
    	}
    	if(N == depth) return 0;
    	
    	return dfs(N, K, depth + 1, arr, sum + arr[depth]) +
    			dfs(N, K, depth + 1, arr, sum);
    }
}