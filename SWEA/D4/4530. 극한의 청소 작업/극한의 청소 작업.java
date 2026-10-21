import java.io.*;
import java.util.*;
/*
26,920 kb
메모리
88 ms
실행시간
 */
public class Solution {
	static Long A, B;
	static int[] diamonds;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			
			
			
			sb.append("#").append(test_case).append(" ");
			sb.append(toDecimal(B) - toDecimal(A) - ((B > 0 && A < 0) ? 1:0));
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static Long toDecimal(long n) {
		
		long sum = 0;
		long pow = 1;
		boolean isNegative = false;
		
		if(n < 0) {
			isNegative = true;
			n = -n;
		}
		
		while (n > 0) {
            long digit = n % 10;
            if (digit > 4) {
                digit--;
            }
            sum += digit * pow;
            pow *= 9;
            n /= 10;
        }
        
        return isNegative ? -sum : sum;
	}
}
