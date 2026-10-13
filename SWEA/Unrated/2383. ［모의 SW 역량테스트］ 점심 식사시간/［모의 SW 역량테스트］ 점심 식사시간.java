import java.io.*;
import java.util.*;

/*
[문제해석]
-식당 내 사람들이 모두 계단을 내려가는 시간을 구하라
-계단은 최대 3명의 사람만 이용 가능
-계단을 내려가는 데는 K분이 걸림
[제약조건]
1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는데, C/C++/Java 모두 3초

2. 방의 한 변의 길이 N은 4 이상 10 이하의 정수이다. (4 ≤ N ≤ 10)

3. 사람의 수는 1 이상 10 이하의 정수이다. (1 ≤ 사람의 수 ≤ 10)

4. 계단의 입구는 반드시 2개이며, 서로 위치가 겹치지 않는다.

5. 계단의 길이는 2 이상 10 이하의 정수이다. (2 ≤ 계단의 길이 ≤ 10)

6. 초기에 입력으로 주어지는 사람의 위치와 계단 입구의 위치는 서로 겹치지 않는다.
[이슈사항]
-계단 안 밖 사람들을 어떻게 관리?
	->계단 속은 큐로 관리
	->대기줄에 넣는 것은 조합으로 경우의 수를 구함
[해결프로세스]
조합 사용
1. 0번사람부터하여 도착시간을 모두 구함
2. 도착시간을 오름차 순으로 정렬
3. 퍼뮤테이션으로 두 계단 중 하나를 택하게 해 flag에 넣음
4. flag & 1 << i가 true인 사람은 stairQ1에 넣고 아니면 stairQ2에 넣음
5. stairQ1과 2가 모두 비어지는 최소 시간을 구함
 */

public class Solution {
	static int N, minTime;
	static int[][] map, stair;
	static int K[];
	static int peopleSize;
	static int positions[][];
	static int distances[][];
	static PriorityQueue<Integer> waitQ1, waitQ2;
	static PriorityQueue<Integer> stairQ1, stairQ2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			minTime = Integer.MAX_VALUE;
			
			waitQ1 = new PriorityQueue<>();
			waitQ2 = new PriorityQueue<>();
			stairQ1 = new PriorityQueue<>();
			stairQ2 = new PriorityQueue<>();
			
			peopleSize = 0;
			positions = new int[10][2];
			distances = new int[10][2];
			
			K = new int[2];
			stair = new int[2][2];
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			int stairCnt = 0;
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						positions[peopleSize][0] = i;
						positions[peopleSize][1] = j;
						peopleSize++;
					}
					else if(map[i][j] > 1) {
						stair[stairCnt][0] = i;
						stair[stairCnt][1] = j;
						K[stairCnt] = map[i][j];
						stairCnt++;
					}
				}
			}
			calcDistances();
			
			combine(0, 1 << peopleSize);
			
			sb.append("#").append(test_case).append(" ").append(minTime).append("\n");
		}
		
		
		System.out.println(sb);
	}
	
	public static void calcDistances() {
		for(int i = 0; i < peopleSize; i++) {
			distances[i][0] = Math.abs(positions[i][0] - stair[0][0]) + Math.abs(positions[i][1] - stair[0][1]);
			distances[i][1] = Math.abs(positions[i][0] - stair[1][0]) + Math.abs(positions[i][1] - stair[1][1]);
		}
	}
	
	public static void combine(int depth, int flag) {
		if(depth == peopleSize) {
			
			for(int i = 0; i < peopleSize; i++) {
				if((flag & 1 << i) != 0) {
					waitQ1.offer(distances[i][0]);
				}
				else {

					waitQ2.offer(distances[i][1]);
				}
			}
//			
//			for(int i = 0; i < peopleSize; i++) {
//				if((flag & 1 << i) != 0) System.out.print(i);
//			}
//			System.out.println(" / ");
//			for(int i = 0; i < peopleSize; i++) {
//				if((flag & 1 << i) == 0) System.out.print(i);
//			}
//			System.out.println();
//			System.out.println("-----------------");
			
			int time = 0;
			while(!stairQ1.isEmpty() || !stairQ2.isEmpty() ||
					!waitQ1.isEmpty() || !waitQ2.isEmpty()) {
				while(!stairQ1.isEmpty() && stairQ1.peek() <= time) {
					stairQ1.poll();
				}
				
				while(stairQ1.size() < 3 && !waitQ1.isEmpty() && waitQ1.peek() <= time) {
					waitQ1.poll();
					stairQ1.offer(time + K[0]);
				}
					
					
				while(!stairQ2.isEmpty() && stairQ2.peek() <= time) {
					stairQ2.poll();
				}
				
				while(stairQ2.size() < 3 && !waitQ2.isEmpty() && waitQ2.peek() <= time) {
					waitQ2.poll();
					stairQ2.offer(time + K[1]);
				}
				time++;
			}
			
			minTime = Math.min(time, minTime);
			return;
		}
		
		combine(depth + 1, flag | 1 << depth);
		combine(depth + 1, flag);
	}
	
}
