import java.io.*;
import java.util.*;

/*
[문제해석]
- 10*10 지도 위 배터리 충전소가 M개 있음
- 사용자가 멘헤탄 거리 기준으로 충전거리 이하이면 해당 위치에서 충전 가능
- 충전소 거리 내에 여러명이 있는 경우 사람 수 만큼 충전양 분배
- 사람 A, B가 각각 0,0 9,9에서 출발하고 이동 경로를 제공할 때 충전할 수 있는 최대 충전량의 합
[제약사항]
1. 지도의 가로, 세로 크기는 10이다.
2. 사용자는 총 2명이며, 사용자A는 지도의 (1, 1) 지점에서, 사용자B는 지도의 (10, 10) 지점에서 출발한다.
3. 총 이동 시간 M은 20이상 100이하의 정수이다. (20 ≤ M ≤ 100)
4. BC의 개수 A는 1이상 8이하의 정수이다. (1 ≤ A ≤ 8)
5. BC의 충전 범위 C는 1이상 4이하의 정수이다. (1 ≤ C ≤ 4)
6. BC의 성능 P는 10이상 500이하의 짝수이다. (10 ≤ P ≤ 500)
7. 사용자의 초기 위치(0초)부터 충전을 할 수 있다.
8. 같은 위치에 2개 이상의 BC가 설치된 경우는 없다. 그러나 사용자A, B가 동시에 같은 위치로 이동할 수는 있다. 사용자가 지도 밖으로 이동하는 경우는 없다.
[이슈사항]
- 두 사람 위치를 뭘로 관리하지?
	-> 객체로 관리하며 시뮬레이션
- 두 사람이 같은 충전소에 있는 걸 어떻게 알지?
	-> 충전소 기준 거리 내에 있는 사람 수 세기
- 겹치는 충전소에 있으면 어떤 걸 선택하게 하지?
	-> 겹치는 충전소 중 가장 높은 충전량 가진 것부터 사용하게 함
	
[해결 프로세스]
 */

public class Solution {
	
	static class User {
		int r, c;
		boolean isCharging;
		public User(int r, int c, boolean isCharging) {
			super();
			this.r = r;
			this.c = c;
			this.isCharging = isCharging;
		}
		
	}
	
	static class Charger implements Comparable<Charger>{
		int x, y, c, p;

		public Charger(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

		@Override
		public int compareTo(Charger o) {
			return o.p - this.p;
		}
		
	}
	
	static int M, A, sum;
	static int[] historyA, historyB;
	static User userA, userB;
	static Charger[] chargers;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case++) {
			sum = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			userA = new User(1, 1, false);
			userB = new User(10, 10, false);
			historyA = new int[M];
			historyB = new int[M];
			
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				historyA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				historyB[i] = Integer.parseInt(st.nextToken());
			}
			
			chargers = new Charger[A];
			for(int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int CX = Integer.parseInt(st.nextToken());
				int CY = Integer.parseInt(st.nextToken());
				int CC = Integer.parseInt(st.nextToken());
				int CP = Integer.parseInt(st.nextToken());
				chargers[i] = new Charger(CX, CY, CC, CP);
			}
			
			Arrays.sort(chargers);
			
			for(int i = 0; i <= M; i++) {
				List<Charger> listA = new ArrayList<>();
				List<Charger> listB = new ArrayList<>();

				listA.add(new Charger(0, 0, 0, 0)); 
				for (int j = 0; j < A; j++) {
				    if (isConnected(chargers[j], userA)) listA.add(chargers[j]);
				}

				listB.add(new Charger(0, 0, 0, 0));
				for (int j = 0; j < A; j++) {
				    if (isConnected(chargers[j], userB)) listB.add(chargers[j]);
				}

				int maxCurrentSum = 0;

				for (Charger ca : listA) {
				    for (Charger cb : listB) {
				        int currentSum = 0;
				        if (ca == cb) { 
				            if (ca.p > 0) {
				                currentSum = ca.p;
				            }
				        } else {
				            currentSum = ca.p + cb.p;
				        }
				        maxCurrentSum = Math.max(maxCurrentSum, currentSum);
				    }
				}

				sum += maxCurrentSum;
				
				if(i == M) continue;
				if(historyA[i] == 1) userA.r--;
				else if(historyA[i] == 2) userA.c++;
				else if(historyA[i] == 3) userA.r++;
				else if(historyA[i] == 4) userA.c--;
				
				if(historyB[i] == 1) userB.r--;
				else if(historyB[i] == 2) userB.c++;
				else if(historyB[i] == 3) userB.r++;
				else if(historyB[i] == 4) userB.c--;
				
			}
			
			sb.append("#").append(test_case).append(" ");
			sb.append(sum);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean isConnected(Charger c, User u) {
		if(Math.abs(c.y - u.r) + Math.abs(c.x - u.c) <= c.c) return true;
		return false;
	}
}
