import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int[][] grid, result;
	static String dir;
	static Queue<Integer> waitQ;
	static Deque<Integer> resultDQ;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			waitQ = new LinkedList<>();
			resultDQ = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			dir = st.nextToken();
			
			grid = new int[N][N];
			result = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			push(dir);
			
			sb.append("#").append(test_case).append("\n");
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					sb.append(result[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	
	public static void push(String dir) {
		
		if(dir.equals("up")) {
			for(int j = 0; j < N; j++) {
				for(int i = 0; i < N; i++) {
					waitQ.offer(grid[i][j]);
				}
				
				getResultDQ();
				
				for(int i = 0; i < N; i++) {
					if(!resultDQ.isEmpty()) result[i][j] = resultDQ.removeFirst();
					else result[i][j] = 0;
				}
			}
		}
		else if(dir.equals("down")) {
			for(int j = 0; j < N; j++) {
				for(int i = N - 1; i >= 0; i--) {
					waitQ.offer(grid[i][j]);
				}
				
				getResultDQ();
				
				for(int i = N - 1; i >= 0; i--) {
					if(!resultDQ.isEmpty()) result[i][j] = resultDQ.removeFirst();
					else result[i][j] = 0;
				}
			}
		}
		else if(dir.equals("left")) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					waitQ.offer(grid[i][j]);
				}
				
				getResultDQ();
				
				for(int j = 0; j < N; j++) {
					if(!resultDQ.isEmpty()) result[i][j] = resultDQ.removeFirst();
					else result[i][j] = 0;
				}
			}
		}
		else if(dir.equals("right")){
			for(int i = 0; i < N; i++) {
				for(int j = N - 1; j >= 0; j--) {
					waitQ.offer(grid[i][j]);
				}
				
				getResultDQ();
				
				for(int j = N - 1; j >= 0; j--) {
					if(!resultDQ.isEmpty()) result[i][j] = resultDQ.removeFirst();
					else result[i][j] = 0;
				}
			}
		}
	}
	
	public static void getResultDQ() {

		while(!waitQ.isEmpty()) {
			int now = waitQ.poll();
			if(now == 0) continue;
			
			resultDQ.addLast(now);
			
			while(!waitQ.isEmpty()) {
				int now2 = waitQ.peek();
				if(now2 == 0) {
					waitQ.poll();
					continue;
				}
				if(now == now2) {
					waitQ.poll();
					resultDQ.addLast(resultDQ.removeLast() * 2);
				}
				break;
			}
		}
	}
}