import java.util.*;
import java.io.*;

/*
[문제해석]
- N*N 산에 가장 긴 등산로를 만드려고 함
- 등산로는 제일 높은 칸에서 시작해 가로, 세로 자신보다 낮은 높이로만 이동할 수 있음
- 한칸의 높이를 K만큼 감소시킬 수 있음
[제약사항]
1. 시간 제한 : 최대 51개 테스트 케이스를 모두 통과하는 데 C/C++/Java 모두 3초

2. 지도의 한 변의 길이 N은 3 이상 8 이하의 정수이다. (3 ≤ N ≤ 8)

3. 최대 공사 가능 깊이 K는 1 이상 5 이하의 정수이다. (1 ≤ K ≤ 5)

4. 지도에 나타나는 지형의 높이는 1 이상 20 이하의 정수이다.

5. 지도에서 가장 높은 봉우리는 최대 5개이다.

6. 지형은 정수 단위로만 깎을 수 있다.

7. 필요한 경우 지형을 깎아 높이를 1보다 작게 만드는 것도 가능하다.
[이슈사항]
-어디를 깎아야 할 지 어떻게 찾지?
	-> 깎지 않는 경우:가장 높은 곳을 기준으로 DFS
	-> 깎는 경우:N*N크기 중 한 곳을 깎은 후 가장 높은 곳을 기준으로 DFS
[해결 프로세스]
N*N
 */
public class Solution {
	static int N, K;
	static int[][] map;
	static int maxDist;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static boolean[][] visited;
	static Set<List<Integer>> startPosList;
	static int maxHeight;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			maxDist = 0;
			startPosList = new HashSet<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N][N];
			map = new int[N][N];
			
			maxHeight = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, map[i][j]);
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == maxHeight) {
						startPosList.add(new ArrayList<>(Arrays.asList(i, j)));
					}
				}
			}
			getDist();
			
			for(int q = 0; q < N; q++) {
				for(int w = 0; w < N; w++) {
					int tmp = map[q][w];
					for(int k = 1; k <= K; k++) {
						map[q][w]--;
						getDist();
					}
					
					
					map[q][w] = tmp;
				}
			}
			
			
			sb.append("#").append(test_case).append(" ");
			sb.append(maxDist);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void getDist() {
		for(List<Integer> pos:startPosList) {
			dfs(pos.get(0), pos.get(1), 1);
		}
	}
	
	public static void dfs(int r, int c, int dist) {
		
		maxDist = Math.max(maxDist, dist);

		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr > N - 1 || nc < 0 || nc > N - 1) continue;
			if(visited[nr][nc]) continue;
			if(map[r][c] <= map[nr][nc]) continue;
			visited[r][c] = true;
			dfs(nr, nc, dist+1);
			visited[r][c] = false;
		}

		
	}
}





