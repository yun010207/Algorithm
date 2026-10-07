import java.io.*;
import java.util.*;

public class Solution {
	
	public static class Edge {
		int e, c;

		public Edge(int e, int c) {
			super();
			this.e = e;
			this.c = c;
		}
		
	}
	
	static int N, M, start, end;
	static int[] dist;
	static ArrayList<ArrayList<Edge>> edges;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			edges = new ArrayList<ArrayList<Edge>>();
			for(int i = 0; i <= N; i++) {
				edges.add(new ArrayList<Edge>());
			}
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				edges.get(s).add(new Edge(e, c));
			}

			dist = new int[N + 1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.c,  o2.c));
			pq.offer(new Edge(start, 0));
			dist[start] = 0;
			while(!pq.isEmpty()) {
				Edge curEdge = pq.poll();
				
				if(dist[curEdge.e]< curEdge.c) continue; 
				
				for(int i = 0; i < edges.get(curEdge.e).size(); i++) {
					Edge nextEdge = edges.get(curEdge.e).get(i);
					
					if(dist[nextEdge.e] > curEdge.c + nextEdge.c) {
						dist[nextEdge.e] = curEdge.c + nextEdge.c;
						
						pq.offer(new Edge(nextEdge.e, dist[nextEdge.e]));
					}
				}
			}
			sb.append("#").append(test_case).append(" ");
			if(dist[end] == Integer.MAX_VALUE) sb.append(-1); 
			else sb.append(dist[end]);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
}
