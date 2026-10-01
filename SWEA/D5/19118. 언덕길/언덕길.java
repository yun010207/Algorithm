import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int LIS[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			
			N = Integer.parseInt(br.readLine());
			LIS = new int[N];
			
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			
			LIS[0] = Integer.parseInt(st.nextToken());

			int maxIdx = 0;
			for(int i = 1; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(num > LIS[maxIdx]) {
					LIS[++maxIdx] = num;
				}
				else {
					int idx = binarySearch(maxIdx, num);
					LIS[idx] = num;
				}
			}
			System.out.println("#"+test_case+" "+(N - (maxIdx + 1)));
			
			
		}
	}
	
	
	public static int binarySearch(int right, int target) {
		int left = 0;
		int mid = 0;
		while(left <= right) {
			mid = (left + right) / 2;
			if(target > LIS[mid]) left = mid + 1;
			else if(target < LIS[mid]) right = mid - 1;
			else {
				return mid;
			}
		}
		return left;
	}
}
