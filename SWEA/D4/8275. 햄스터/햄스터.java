import java.io.*; 
import java.util.*;

public class Solution {
	
	static int N, X, M, maxCnt;
	static int hamsters[];
	static int best[];
	
	static List<int[]> limits;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			maxCnt = -1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			limits = new ArrayList<>();
			hamsters = new int[N + 1];
			best = new int[N + 1];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				limits.add(new int[] {l, r, s});
			}
			
			getMaxHamsters(1, 0);
			
			
			
			System.out.print("#"+test_case);
			if(maxCnt < 0) System.out.print(" -1");
			else {
				for(int i = 1; i <= N; i++) System.out.print(" "+best[i]);
			}
			System.out.println();
		}
		
		
		
	}
	
	public static void getMaxHamsters(int depth, int cnt) {
		if(depth == N + 1) {
			
			for(int[] arr:limits) {
				int sum = 0;
				for(int i = arr[0]; i <= arr[1]; i++) {
					sum += hamsters[i];
				}
				if(sum != arr[2]) return;
			}
			
			if(maxCnt < cnt) {
				maxCnt = cnt;
				best = hamsters.clone();
			}
			return;
		}
		
		for(int i = 0; i <= X; i++) {
			hamsters[depth] = i;
			getMaxHamsters(depth + 1, cnt + i);
		}
	}
}