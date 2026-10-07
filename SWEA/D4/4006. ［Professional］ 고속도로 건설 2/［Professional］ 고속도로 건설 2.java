import java.io.*;
import java.util.*;

public class Solution {
	
	public static class Edge{
		int s, e, c;

		public Edge(int s, int e, int c) {
			super();
			this.s = s;
			this.e = e;
			this.c = c;
		}
		
	}
	
	static int N, M;
	static PriorityQueue<Edge> edges;
	static int parent[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			edges = new PriorityQueue<Edge>((o1, o2) -> {
				return o1.c - o2.c;
			});
			
			N = Integer.parseInt(br.readLine());
			parent = new int[N + 1];
			Arrays.fill(parent, -1);
			
			M = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				edges.add(new Edge(s, e, c));
			}
			
			int edgeCount = 0;
			int totalDist = 0;
			while(!edges.isEmpty() && edgeCount < N - 1) {
				Edge newEdge = edges.poll();
				
				if(!union(newEdge.s, newEdge.e)) continue;
				
				edgeCount++;
				totalDist += newEdge.c;
			}
			
			sb.append("#").append(test_case).append(" ").append(totalDist).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int find(int x) {
		if(parent[x] < 0) return x;
		return parent[x] = find(parent[x]);
	}
	
	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return false;
		
		if(parent[x] < parent[y]) {
			parent[x] = parent[y];
			parent[y] = x;
		}
		else {
			parent[y] = parent[x];
			parent[x] = y;
		}
		return true;
	}
}
