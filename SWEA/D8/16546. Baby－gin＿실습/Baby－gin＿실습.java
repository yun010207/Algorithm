import java.io.*;
import java.util.*;

public class Solution {
	
	static int input[];
	static int cards[];
	static boolean isBabyGin;
	static int used;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			isBabyGin = false;
			cards = new int[6];
			input = new int[6];
			used = -1;
			String str = br.readLine();
			for(int i = 0; i < 6; i++) {
				input[i] = str.charAt(i) - '0';
			}
			
			dfs(0, 1 << 6);
			System.out.println("#"+test_case+" "+isBabyGin);
		}
	}
	
	public static void dfs(int depth, int flag) {
		if(isBabyGin) return;
		if(depth == 6) {
			int cnt = 0;
			if(isRun(0)) {
//				System.out.println("RUN0");
				cnt++;
			}
			if(isRun(3)) {
//				System.out.println("RUN3");
				cnt++;
			}
			if(isTriplet(0)) {
//				System.out.println("TRI0");
				cnt++;
			}
			if(isTriplet(3)) {
//				System.out.println("TRI3");
				cnt++;
			}
			
			if(cnt == 2) isBabyGin = true;
			return;
		}
		
		
		for(int i = 0; i < 6; i++) {
			if((flag & 1 << i) != 0) continue;
			cards[depth] = input[i];
			dfs(depth + 1, flag | 1 << i);
		}
		
		
	}
	
	public static boolean isRun(int start) {
		
		if(cards[start] == cards[start + 1] + 1 &&
			cards[start + 1] + 1 == cards[start + 2] + 2) 
			return true;
		
		if(cards[start] == cards[start + 1] - 1 &&
				cards[start + 1] - 1 == cards[start + 2] - 2) 
			return true;
		
		return false;
	}
	
	public static boolean isTriplet(int start) {
		if(cards[start] == cards[start + 1] &&
			cards[start + 1] == cards[start + 2]) 
			return true;
			
		return false;
	}
}
