import java.io.*;
import java.util.*;

public class Solution {
	static int N, K;
	static String[] numbers;
	static int[] len, numMod, pow10;
	static long dp[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			numbers = br.readLine().split(" ");
			K = Integer.parseInt(br.readLine());
			
			len = new int[N];
			numMod = new int[N];
			for(int i = 0; i < N; i++) {
				len[i] = numbers[i].length();
				int m = 0;
				for(int j = 0; j < len[i]; j++) {
					m = (m * 10 + (numbers[i].charAt(j) - '0')) % K;
				}
				numMod[i] = m;
			}
			
			pow10 = new int[51];
			pow10[0] = 1 % K;
			for(int i = 1; i <= 50; i++) {
				pow10[i] = (pow10[i - 1] * 10) % K;
			}
			
			dp = new long[1 << N][K];
			dp[0][0] = 1;
			
			for (int visited = 0; visited < (1 << N); visited++) {
                for (int rem = 0; rem < K; rem++) {
                    if (dp[visited][rem] == 0) continue;

                    for (int i = 0; i < N; i++) {
                        if ((visited & (1 << i)) == 0) {
                            int nextVisited = visited | (1 << i);
                            int nextRem = (rem * pow10[len[i]] + numMod[i]) % K;
                            dp[nextVisited][nextRem] += dp[visited][rem];
                        }
                    }
                }
            }
			
			sb.append("#").append(test_case).append(" ").append(dp[(1<<N) - 1][0]).append("\n");
		}
		System.out.println(sb);
	}
}
