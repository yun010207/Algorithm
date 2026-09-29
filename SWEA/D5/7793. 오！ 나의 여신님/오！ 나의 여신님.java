import java.io.*;
import java.util.*;

public class Solution {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static char[][] map;
	static int[][] polluted, dist;
	static int N, M;
	static int godR, godC;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			polluted = new int[N][M];
			dist = new int[N][M];
			for(int i = 0; i < N; i++) {
				Arrays.fill(polluted[i], Integer.MAX_VALUE);
			}
			for(int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			Queue<int[]> pollutions = new LinkedList<>();
			Queue<int[]> suyeons = new LinkedList<>();
			for(int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 'D') {
						godR = i;
						godC = j;
					}
					else if(map[i][j] == 'S') {
						suyeons.add(new int[] {i, j});
						dist[i][j] = 0;
					}
					else if(map[i][j] == '*') {
						pollutions.add(new int[] {i, j});
						polluted[i][j] = 0;
					}
				}
			}
			
			while(!pollutions.isEmpty()) {
				int[] p = pollutions.poll();
				
				for(int d = 0; d < 4; d++) {
					int nr = p[0] + dr[d];
					int nc = p[1] + dc[d];
					
					if(nr < 0 || nr > N - 1 || nc < 0 || nc > M - 1) continue;
					if(map[nr][nc] == 'D' || map[nr][nc] == 'X') continue;
					if(polluted[p[0]][p[1]] + 1 >= polluted[nr][nc]) continue;
					
					polluted[nr][nc] = polluted[p[0]][p[1]] + 1;
					pollutions.add(new int[] {nr, nc});
				}
			}
			
			while(!suyeons.isEmpty()) {
				int[] s = suyeons.poll();
				
				for(int d = 0; d < 4; d++) {
					int nr = s[0] + dr[d];
					int nc = s[1] + dc[d];
					
					if(nr < 0 || nr > N - 1 || nc < 0 || nc > M - 1) continue;
					if(map[nr][nc] == 'X' || (dist[s[0]][s[1]] + 1) >= polluted[nr][nc]) continue;
					if(dist[s[0]][s[1]] + 1 >= dist[nr][nc]) continue;
					
					dist[nr][nc] = dist[s[0]][s[1]] + 1;
					suyeons.add(new int[] {nr, nc});
				}
			}
			if(dist[godR][godC] == Integer.MAX_VALUE) System.out.println("#"+test_case+" GAME OVER");
			else System.out.println("#"+test_case+" "+dist[godR][godC]);
		}
		
		
	}
	
	
}