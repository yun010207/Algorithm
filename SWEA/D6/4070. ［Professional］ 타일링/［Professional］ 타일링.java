import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Solution {
	
	static BigInteger dp[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			dp = new BigInteger[N + 1];
			dp[0] = new BigInteger("1");
			dp[1] = new BigInteger("1");
			
			BigInteger two = new BigInteger("2");
			for(int i = 2; i <= N; i++) {
				dp[i] = dp[i - 2].multiply(two).add(dp[i - 1]);
			}
			
			
			sb.append("#").append(test_case).append(" ");
			sb.append(dp[N]);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
}





