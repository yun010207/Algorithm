import java.io.*;
import java.util.*;
/*
34,876 kb
메모리
162 ms
실행시간
 */
public class Solution {
	
	static int N;
	static int prob[][];
	static double maxProb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case++) {
			maxProb = 0.0d;
			N = Integer.parseInt(br.readLine());
			prob = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					prob[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0, 1.0d, 0);
			
			sb.append("#").append(test_case).append(" ");
			sb.append(String.format("%.6f", maxProb * 100));
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int depth, double sum, int flag) {
		if(sum <= maxProb) return;
		if(depth == N) {
			maxProb = Math.max(maxProb, sum);
			return;
		}
		for(int i = 0; i < N; i++) {
			if((flag & 1 << i) != 0) continue;
			
			double temp = sum * ((double)prob[depth][i] * 0.01d);
			dfs(depth + 1, temp, flag | 1 << i);
		}
	}
}
