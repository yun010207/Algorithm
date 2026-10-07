import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, lines[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			lines = new int[N][2];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				lines[i][0] = Integer.parseInt(st.nextToken());
				lines[i][1] = Integer.parseInt(st.nextToken());
				
				
			}
			int cnt = 0;
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N; j++) {
					int Ai = lines[i][0];
					int Bi = lines[i][1];
					int Aj = lines[j][0];
					int Bj = lines[j][1];
					
					if((Ai > Aj && Bi < Bj) ||
						(Ai < Aj && Bi > Bj)) cnt++;
				}
			}
			sb.append("#").append(test_case).append(" ").append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
