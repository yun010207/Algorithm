import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int[] scores, dp;
	static List<Integer> list;
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case++) {
			list = new ArrayList<>();
			
			N = Integer.parseInt(br.readLine());
			scores = new int[N];
			int total = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				scores[i] = Integer.parseInt(st.nextToken());
				total += scores[i];
			}
			visited = new boolean[total + 1];
			list.add(0);
			visited[0] = true;
			
			for(int i = 0; i < N; i++) {
				int size = list.size();
				for(int j = 0; j < size; j++) {
					int sum = list.get(j) + scores[i];
					if(visited[sum]) continue;
					visited[sum] = true;
					list.add(sum);
				}
			}
			
			
			sb.append("#").append(test_case).append(" ");
			sb.append(list.size());
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
