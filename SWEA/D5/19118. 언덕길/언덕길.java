import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static int[] LIS;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			LIS = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			LIS[0] = Integer.parseInt(st.nextToken());
			int maxIdx = 0;
			for(int i = 1; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				if(LIS[maxIdx] < num) LIS[++maxIdx] = num;
				else LIS[binarySearch(0, maxIdx, num)] = num;
			}
			
			sb.append("#").append(test_case).append(" ");
			sb.append(N - (maxIdx + 1));
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static int binarySearch(int left, int right, int num) {
		while(left <= right) {
			int mid = (left + right) / 2;
			if(LIS[mid] > num) right = mid - 1;
			else if(LIS[mid] < num) left = mid + 1;
			else return mid;
		}
		return left;
	}
}





