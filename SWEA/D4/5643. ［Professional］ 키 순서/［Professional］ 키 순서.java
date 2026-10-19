import java.util.*;
import java.io.*;


public class Solution {
	
	static class Node {
		int num;
		ArrayList<Integer> bigger, smaller;
		
		Node(int num) {
			bigger = new ArrayList<>();
			smaller = new ArrayList<>();
		}
	}
	static int N, M;
	static Node[] students;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			
			N = Integer.parseInt(br.readLine());
			students = new Node[N + 1];
			for(int i = 1; i <= N; i++) students[i] = new Node(i);

			M = Integer.parseInt(br.readLine());
			for(int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				students[a].bigger.add(b);
				students[b].smaller.add(a);
			}
			
			int cnt = 0;
			for(int i = 1; i <= N; i++) {
				if(bfs(i)) cnt++;
			}
			
			
			
			sb.append("#").append(test_case).append(" ");
			sb.append(cnt);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static boolean bfs(int num) {
		boolean[] visited = new boolean[N + 1];
		visited[num] = true;
		int cnt = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(num);
		while(!q.isEmpty()) {
			int nowStudent = q.poll();
			for(int i : students[nowStudent].bigger) {
				if(!visited[i]) {
					q.add(i);
					visited[i] = true;
					cnt++;
				}
				
			}
		}
		q.offer(num);
		while(!q.isEmpty()) {
			int nowStudent = q.poll();
			for(int i : students[nowStudent].smaller) {
				if(!visited[i]) {
					q.add(i);
					visited[i] = true;
					cnt++;
				}
				
			}
		}
		if(cnt == N - 1) return true;
		else return false;
	}
}





