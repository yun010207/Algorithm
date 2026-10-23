import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case++) {
			
			int N = Integer.parseInt(br.readLine());
			boolean[] checked = new boolean[N + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());

			int cnt = 0;
			for(int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				checked[num] = true;
				if(checked[num - 1] != true) cnt++;
			}
			
			
			sb.append("#").append(test_case).append(" ");
			sb.append(cnt);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
