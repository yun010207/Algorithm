import java.io.*;
import java.util.*;


public class Solution {
	
	static class Node {
		int r, c, m, d;

		public Node(int r, int c, int d, int m) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.m = m;
		}
		
	}
	
	static int N, M;
	static char[][] map;
	static int dr[] = {0, 0, 1, -1};
	static int dc[] = {1, -1, 0, 0};
	static boolean isEnd;
	static boolean[][][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case++) {
			isEnd = false;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N][M][4][16];
			
			map = new char[N][M];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				map[i] = st.nextToken().toCharArray();
			}
			
			
			Queue<Node> q = new LinkedList<>();
			q.offer(new Node(0, 0, 0, 0));
			while(!q.isEmpty()) {
				Node node = q.poll();
				while(!visited[node.r][node.c][node.d][node.m]) {
					visited[node.r][node.c][node.d][node.m] = true;
					char order = map[node.r][node.c];
					if(order == '<') {
						node.d = 1;
					}
					else if (order == '>') {
						node.d = 0;
					}
					else if (order == '^') {
						node.d = 3;
					}
					else if (order == 'v') {
						node.d = 2;
					}
					else if (order == '_') {
						if(node.m == 0) node.d = 0;
						else node.d = 1;
					}
					else if (order == '|') {
						if(node.m == 0) node.d = 2;
						else node.d = 3;
					}
					else if (order == '?') {
						
						
						for(int i = 0; i <= 3; i++) {
							int r1 = (node.r + dr[i] + N) % N;
							int c1 = (node.c + dc[i] + M) % M;
							if(!visited[r1][c1][i][node.m]) q.offer(new Node(r1, c1, i, node.m));
						}
						break;
					}
					else if (order == '@') {
						isEnd = true;
						break;
					}
					else if (order == '+') {
						node.m = (node.m + 1) % 16;
					}
					else if (order == '-') {
						node.m = (node.m - 1 + 16) % 16;
					}
					else if (order >= '0' && order <= '9'){
						node.m = order - '0';
					}
					move(node);
				}
			}
			
			sb.append("#").append(test_case).append(" ");
			if(isEnd) sb.append("YES");
			else sb.append("NO");
			sb.append("\n");
		}
		System.out.println(sb);
	}
	

	
	static void move(Node node) {
		node.r += dr[node.d];
		node.c += dc[node.d];
		node.r = (node.r + N) % N;
		node.c = (node.c + M) % M;
		
	}
}
